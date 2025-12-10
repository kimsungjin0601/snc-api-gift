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
public class SignRequest {
    @Schema(description = "로그인ID")
    private String loginId;

    @Schema(description = "패스워드")
    private String password;

    @Schema(description = "사용자명")
    private String userName;

    @Schema(description = "전화번호")
    private String phone;

    @Schema(description = "마케팅_수신동의")
    private Boolean isMarketing;
}
