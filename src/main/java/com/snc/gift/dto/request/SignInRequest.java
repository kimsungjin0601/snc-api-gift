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
public class SignInRequest {
    @Schema(description = "로그인_ID")
    private String loginId;

    @Schema(description = "패스워드")
    private String password;
}
