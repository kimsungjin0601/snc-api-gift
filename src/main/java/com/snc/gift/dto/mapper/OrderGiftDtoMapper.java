package com.snc.gift.dto.mapper;

import com.cstify.common.dto.PageRequest;
import com.snc.gift.dto.response.OrderGiftCompleteResponse;
import com.snc.gift.dto.response.OrderGiftUserListDto;
import com.snc.gift.dto.search.OrderGiftUserSearch;
import com.snc.gift.vo.OrderGiftVo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface OrderGiftDtoMapper {
    OrderGiftUserSearch toOrderGiftUserSearch(PageRequest request);

    List<OrderGiftUserListDto> toOrderGiftUserDtoList(List<OrderGiftVo> voList);

    OrderGiftCompleteResponse toOrderGiftCompleteResponse(OrderGiftVo vo);

}
