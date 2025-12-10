package com.snc.gift.service;

import com.cstify.common.dto.PageDto;
import com.cstify.common.dto.PageRequest;
import com.cstify.common.dto.PageResponse;
import com.cstify.common.util.CodeUtil;
import com.cstify.common.util.ResponseUtil;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.domain.OrderGift;
import com.snc.gift.domain.OrderGiftItem;
import com.snc.gift.domain.OrderGiftStatusHistory;
import com.snc.gift.dto.mapper.OrderGiftDtoMapper;
import com.snc.gift.dto.request.DenominationCreateDto;
import com.snc.gift.dto.request.OrderGiftCreateRequest;
import com.snc.gift.dto.response.OrderGiftCompleteResponse;
import com.snc.gift.dto.response.OrderGiftCreateResponse;
import com.snc.gift.dto.response.OrderGiftUserListDto;
import com.snc.gift.dto.search.OrderGiftUserSearch;
import com.snc.gift.mapper.GiftMapper;
import com.snc.gift.mapper.OrderGiftMapper;
import com.snc.gift.mapper.PartnerMapper;
import com.snc.gift.type.OrderStatusType;
import com.snc.gift.vo.GiftVo;
import com.snc.gift.vo.OrderGiftVo;
import com.snc.gift.vo.PartnerVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderGiftService {
    @Value("${aws.s3.domain}")
    String s3Domain;

    private final OrderGiftDtoMapper orderGiftDtoMapper;
    private final OrderGiftMapper orderGiftMapper;
    private final PartnerMapper partnerMapper;
    private final GiftMapper giftMapper;

    public PageResponse<OrderGiftUserListDto> getOrderList(UserInfo userInfo, PageRequest params){
        OrderGiftUserSearch search = orderGiftDtoMapper.toOrderGiftUserSearch(params, userInfo.getUserNo());  // 검색_DTO
        Integer totCnt = orderGiftMapper.getOrderUserCount(search);         // 건수_조회
        List<OrderGiftVo> list = orderGiftMapper.getOrderUserList(search);  // 목록_조회
        return ResponseUtil.getPageResponse(PageDto.of(params, totCnt), orderGiftDtoMapper.toOrderGiftUserDtoList(list));
    }

    public OrderGiftCreateResponse createOrder(UserInfo userInfo, OrderGiftCreateRequest params){
        String orderCode = CodeUtil.orderCode();

        PartnerVo partner = partnerMapper.getUserPartner(userInfo.getUserNo());   // 파트너 정보

        // 대표_상품
        String mainProductName = null;
        if(params != null){
            GiftVo giftVo = giftMapper.getGiftDetail(params.getDenominationList().getFirst().getProductCode(), s3Domain);
            mainProductName = giftVo.getProductName();
        }

        // insert order_gift
        OrderGift order = OrderGift.builder()
                            .partnerNo(partner.getPartnerNo())
                            .parentPartnerNo(partner.getParentPartnerNo())
                            .userNo(userInfo.getUserNo())
                            .orderCode(orderCode)
                            .orderStatusCd(OrderStatusType.READY.getCode())
                            .mainProductName(mainProductName)
                            .ordererName(userInfo.getUserName())
                            .ordererPhone(userInfo.getPhone())
                            .ordererEmail(userInfo.getEmail())
                            .createdBy(userInfo.getUserNo())
                            .updatedBy(userInfo.getUserNo())
                            .build();
        orderGiftMapper.insertOrder(order);

        // insert order_gift_item
        if(params != null){
            for(DenominationCreateDto item :params.getDenominationList()){
                GiftVo gift = giftMapper.getGiftDenominationByCode(item.getDenominationCode());    // 상품권_권종_정보
                orderGiftMapper.insertOrderItem(OrderGiftItem.builder()
                        .orderNo(order.getOrderNo())
                        .productNo(gift.getProductNo())
                        .denominationNo(gift.getDenominationNo())
                        .partnerDomainNo(partner.getPartnerDomainNo())
                        .productName(gift.getProductName())
                        .qty(item.getQty())
                        .unitPrice(gift.getSalePrice())
                        .discountRate(gift.getDiscountRate())
                        .discountPrice(gift.getDiscountPrice())
                        .finalPrice(item.getQty() * gift.getSalePrice())
                        .createdBy(userInfo.getUserNo())
                        .updatedBy(userInfo.getUserNo())
                        .build());
            }
        }

        // DB에서 금액 계산
        orderGiftMapper.updateOrderAmount(order.getOrderNo());

        // insert order_gift_status_history
        orderGiftMapper.insertOrderStatusHistory(OrderGiftStatusHistory.builder()
                        .orderNo(order.getOrderNo())
                        .prevStatusCd(null)
                        .statusCd(order.getOrderStatusCd())
                        .createdBy(userInfo.getUserNo())
                        .build());

        return OrderGiftCreateResponse.builder().orderCode(orderCode).build();
    }

    public OrderGiftCompleteResponse getOrderComplete(UserInfo userInfo, String orderCode){
        OrderGiftVo orderGift = orderGiftMapper.getOrderUserDetailByCode(userInfo.getUserNo(), orderCode);
        return orderGiftDtoMapper.toOrderGiftCompleteResponse(orderGift);
    }
}
