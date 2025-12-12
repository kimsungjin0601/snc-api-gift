package com.snc.gift.service;

import com.cstify.common.provider.TokenProvider;
import com.cstify.common.service.RedisTokenService;
import com.cstify.common.service.TokenBlockService;
import com.cstify.common.util.CodeUtil;
import com.cstify.common.vo.SecurityUserDetails;
import com.cstify.common.vo.TokenPayload;
import com.snc.gift.dto.response.TokenResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Value("${security.token.redis.ttl}")
    private long ttl;

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final RedisTokenService redisTokenService;
    private final TokenBlockService tokenBlockService;

    public TokenResponse login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        // 인증 처리
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 인증 성공 후 처리
        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
        assert userDetails != null;
        String accessToken = processLoginSuccess(userDetails, request, response);

        // AccessToken 반환
        return TokenResponse.builder().accessToken(accessToken).build();
    }

    public String processLoginSuccess(SecurityUserDetails userDetails, HttpServletRequest request, HttpServletResponse response){

        // 이전 접속한 토튼 block 처리
        Long userNo = userDetails.userInfo().getUserNo();
        String userTokenKey = redisTokenService.getTokenKey(userNo);
        TokenPayload tokenPayload = redisTokenService.getTokens(userTokenKey);
        if (userTokenKey != null && tokenPayload != null) {
            tokenBlockService.blockToken(tokenPayload.getAccessToken(), userTokenKey, userNo);
        }

        // 토큰키 및 토큰 발급
        String tokenKey = CodeUtil.tokenKey();
        String accessToken = tokenProvider.createAccessToken(userDetails);
        String refreshToken = tokenProvider.createRefreshToken(userDetails);

        // 토큰 저장
        redisTokenService.saveTokens(tokenKey, accessToken, refreshToken, request);

        // tokenKey를 HttpOnly, Secure 쿠키로 설정
        ResponseCookie cookie = ResponseCookie.from("tokenKey", tokenKey)
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict") // Lax or Strict
                .path("/api/auth/token") 	// 재발급 요청 에만 쿠키 전송
                .maxAge(Duration.ofSeconds(ttl)) // TTL 설정
                .build();

        // 응답에 쿠키 설정
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return accessToken;
    }
}
