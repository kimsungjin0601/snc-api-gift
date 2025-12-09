package com.snc.gift.mapper;

import com.snc.gift.dto.search.OrderGiftUserSearch;
import com.snc.gift.vo.OrderGiftVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderGiftMapper {
    Integer getOrderUserCount(OrderGiftUserSearch params);

    List<OrderGiftVo> getOrderUserList(OrderGiftUserSearch params);
}
