package com.snc.gift.dto.mapper;

import com.snc.gift.dto.response.DenominationDto;
import com.snc.gift.dto.response.GiftDetailDto;
import com.snc.gift.dto.response.GiftMainDto;
import com.snc.gift.vo.GiftVo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface GiftDtoMapper {
    List<GiftMainDto> toGiftMainDtoList(List<GiftVo> voList);

    GiftDetailDto toGiftDetailDto(GiftVo vo);

    List<DenominationDto> toDenominationDtoList(List<GiftVo> voList);
}
