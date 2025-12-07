package com.snc.gift.controller;

import com.snc.gift.dto.response.TokenResponse;
import com.snc.gift.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @Operation(summary = "Access 토큰 발급", description = "Access 토큰 발급")
    @PostMapping("/token")
    public TokenResponse issueAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String newAccessToken = tokenService.issueAccessToken(request, response);
        return TokenResponse.builder().accessToken(newAccessToken).build();
    }
}
