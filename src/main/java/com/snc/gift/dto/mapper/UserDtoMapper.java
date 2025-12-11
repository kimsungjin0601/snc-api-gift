package com.snc.gift.dto.mapper;

import com.snc.gift.domain.User;
import com.snc.gift.dto.request.MemberUpdateRequest;
import com.snc.gift.dto.request.SignUpRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface UserDtoMapper {
    User toUser(SignUpRequest request);

    User toUser(MemberUpdateRequest request);
}
