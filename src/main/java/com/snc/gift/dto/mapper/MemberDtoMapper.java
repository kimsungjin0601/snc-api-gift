package com.snc.gift.dto.mapper;

import com.snc.gift.domain.Member;
import com.snc.gift.dto.request.MemberUpdateRequest;
import com.snc.gift.dto.response.MemberInfoResponse;
import com.snc.gift.vo.MemberVo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface MemberDtoMapper {
    MemberInfoResponse toMemberInfoResponse(MemberVo vo);

    Member toMember(MemberUpdateRequest request);
}
