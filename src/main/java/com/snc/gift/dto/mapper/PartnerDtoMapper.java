package com.snc.gift.dto.mapper;

import com.cstify.common.dto.PageRequest;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.dto.search.PartnerSearch;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface PartnerDtoMapper {
    PartnerSearch toPartnerSearch(PageRequest pageRequest, UserInfo userInfo);
}
