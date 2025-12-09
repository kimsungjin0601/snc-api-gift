package com.snc.gift.service;

import com.cstify.common.dto.PageDto;
import com.cstify.common.dto.PageRequest;
import com.cstify.common.dto.PageResponse;
import com.cstify.common.util.ResponseUtil;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.dto.mapper.OrderGiftDtoMapper;
import com.snc.gift.dto.search.OrderGiftUserSearch;
import com.snc.gift.mapper.OrderGiftMapper;
import com.snc.gift.vo.OrderGiftVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderGiftService {
    private final OrderGiftDtoMapper orderGiftDtoMapper;
    private final OrderGiftMapper orderGiftMapper;

    public PageResponse getOrderList(UserInfo userInfo, PageRequest params){
        OrderGiftUserSearch search = orderGiftDtoMapper.toOrderGiftUserSearch(params);
        search.setUserNo(userInfo.getUserNo());

        Integer totCnt = orderGiftMapper.getOrderUserCount(search);         // 건수 조회
        List<OrderGiftVo> list = orderGiftMapper.getOrderUserList(search);  // 목록 조회
        return ResponseUtil.getPageResponse(PageDto.of(params, totCnt), orderGiftDtoMapper.toOrderGiftUserDtoList(list));
    }
}
