package com.snc.gift.security;

import com.cstify.common.provider.TokenProvider;
import com.cstify.common.service.RedisTokenService;
import com.cstify.common.service.TokenBlockService;
import com.cstify.common.util.CodeUtil;
import com.cstify.common.vo.SecurityUserDetails;
import com.cstify.common.vo.TokenPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Value("${security.token.redis.ttl}")
	private long ttl;

	private final TokenProvider tokenProvider;
	private final RedisTokenService redisTokenService;
	private final TokenBlockService tokenBlockService;
	private final ObjectMapper objectMapper;

	@Override
    @NullMarked
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {

		SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();

		// blacklist 처리
        assert userDetails != null;
        Long userNo = userDetails.userInfo().getUserNo();
		String userTokenKey = redisTokenService.getTokenKey(userNo);
		TokenPayload tokenPayload = redisTokenService.getTokens(userTokenKey);
		if (userTokenKey != null && tokenPayload != null) {
			tokenBlockService.blockToken(tokenPayload.getAccessToken(), userTokenKey, userNo);
		}

		// 1. 토큰키 및 토큰 발급
		String tokenKey = CodeUtil.tokenKey();
		String accessToken = tokenProvider.createAccessToken(userDetails);
		String refreshToken = tokenProvider.createRefreshToken(userDetails);

		// 2. 토큰 저장
		redisTokenService.saveTokens(tokenKey, accessToken, refreshToken, request);

		// 3. tokenKey를 HttpOnly, Secure 쿠키로 설정
		ResponseCookie cookie = ResponseCookie.from("tokenKey", tokenKey)
				.httpOnly(true)
				.secure(true)
				.sameSite("Strict") 	// Lax or Strict
				.path("/api/auth") 	// 재발급 요청 에만 쿠키 전송
				.maxAge(Duration.ofSeconds(ttl)) // TTL 설정
				.build();

		// 4. 응답에 쿠키 설정
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

		if(request.getContentType() != null && request.getContentType().contains("application/json")) {
			super.clearAuthenticationAttributes(request);

			response.setStatus(HttpStatus.OK.value());
	        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
	        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

	        Map<String, String> data = new HashMap<>();
	        data.put("accessToken", accessToken);
			objectMapper.writeValue(response.getOutputStream(), data);

	        return;
		}

		super.onAuthenticationSuccess(request, response, authentication);
	}
}
