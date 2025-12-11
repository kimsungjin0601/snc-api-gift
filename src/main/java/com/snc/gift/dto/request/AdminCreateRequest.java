package com.snc.gift.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminCreateRequest {
    @Schema(description="로그인_ID")
    private String loginId;

    @Schema(description="패스워드")
    private String password;

    @Schema(description="사용자명")
    private String userName;

    @Schema(description="파트너_유형코드 (SUPER_HQ, HQ, AGENT)")
    private String partnerTypeCd;

    @Schema(description="역활_유형코드 (ADMIN, ORDER)")
    private String roleCd;
}
