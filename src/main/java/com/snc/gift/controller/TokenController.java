package com.snc.gift.controller;

import com.snc.gift.dto.response.TokenResponse;
import com.snc.gift.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "공통-토큰", description = "토큰 관련 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200" , description = "OK"),
        @ApiResponse(responseCode = "500" , description = "Internal Server Error")
})
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @Operation(summary = "Access 토큰 재발급", description = "Access 토큰 재발급")
    @PostMapping("/token")
    public TokenResponse issueAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String newAccessToken = tokenService.issueAccessToken(request, response);
        return TokenResponse.builder().accessToken(newAccessToken).build();
    }
}
