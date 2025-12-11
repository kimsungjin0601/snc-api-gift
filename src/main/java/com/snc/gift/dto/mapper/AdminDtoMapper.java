package com.snc.gift.dto.mapper;

import com.cstify.common.dto.PageRequest;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.domain.User;
import com.snc.gift.dto.request.AdminCreateRequest;
import com.snc.gift.dto.response.AdminListDto;
import com.snc.gift.dto.search.AdminSearch;
import com.snc.gift.vo.AdminVo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface AdminDtoMapper {
    AdminSearch toAdminSearch(PageRequest pageRequest, UserInfo userInfo);

    List<AdminListDto> toAdminDtoList(List<AdminVo> voList);

    User toUser(AdminCreateRequest request, String userGrade, String userStatusCd);
}
