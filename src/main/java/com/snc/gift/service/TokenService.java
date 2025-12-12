package com.snc.gift.service;

import com.cstify.common.exception.CookieExpiredException;
import com.cstify.common.provider.TokenProvider;
import com.cstify.common.service.RedisTokenService;
import com.cstify.common.service.TokenBlockService;
import com.cstify.common.util.RequestUtil;
import com.cstify.common.vo.TokenPayload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${security.token.redis.ttl}")
    private long ttl;

    private final TokenProvider tokenProvider;
    private final RedisTokenService redisTokenService;
    private final TokenBlockService tokenBlockService;

    public String issueAccessToken(HttpServletRequest request, HttpServletResponse response){
        String tokenKey = RequestUtil.extractCookie(request, "tokenKey");
        if(tokenKey == null){
            throw new CookieExpiredException();
        }

        // 1. Refresh 토큰 검증
        TokenPayload payload = redisTokenService.getTokens(tokenKey);
        tokenProvider.validateRefreshToken(payload.getRefreshToken());

        Long userNo = tokenProvider.getUserNo(payload.getRefreshToken());
        List<String> roles = tokenProvider.getRoles(payload.getRefreshToken());

//        // 2. IP + Device 체크
//        tokenBlockService.validateClientInfo(tokenKey, userNo, payload, request);

        // 3. Access 토큰 발급
        String newAccessToken = tokenProvider.createAccessToken(userNo, roles);

        // 4. 토큰 저장
        redisTokenService.saveTokens(tokenKey, newAccessToken, payload.getRefreshToken(), request);

        // 5. tokenKey를 HttpOnly, Secure 쿠키로 설정
        ResponseCookie cookie = ResponseCookie.from("tokenKey", tokenKey)
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict") 	// Lax or Strict
                .path("/api/auth/token") 	// 재발급 요청 에만 쿠키 전송
                .maxAge(Duration.ofSeconds(ttl)) // TTL 설정
                .build();

        // 6. 응답에 쿠키 설정
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return newAccessToken;
    }
}
