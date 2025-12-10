package com.snc.gift.mapper;

import com.snc.gift.domain.OrderGift;
import com.snc.gift.domain.OrderGiftItem;
import com.snc.gift.domain.OrderGiftStatusHistory;
import com.snc.gift.dto.search.OrderGiftUserSearch;
import com.snc.gift.vo.OrderGiftVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderGiftMapper {
    Integer getOrderUserCount(OrderGiftUserSearch params);

    List<OrderGiftVo> getOrderUserList(OrderGiftUserSearch params);

    OrderGiftVo getOrderUserDetailByCode(@Param("userNo") Long userNo, @Param("orderCode") String orderCode);

    void insertOrder(OrderGift params);

    void insertOrderItem(OrderGiftItem params);

    void insertOrderStatusHistory(OrderGiftStatusHistory params);

    void updateOrderAmount(Long orderNo);
}
