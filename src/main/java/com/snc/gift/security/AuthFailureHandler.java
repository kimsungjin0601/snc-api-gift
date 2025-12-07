package com.snc.gift.security;

import com.cstify.common.response.SimpleBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private final ObjectMapper objectMapper;

    @Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {

		String msg = switch (exception) {
            case InternalAuthenticationServiceException ignored -> "Auth.no.id";    // 계정이 미존재
            case UsernameNotFoundException ignored -> "Auth.no.id";                 // 계정이 미존재
            case BadCredentialsException ignored -> "Auth.wrong.password";          // 아이디 또는 비밀번호 불일치
            case AuthenticationCredentialsNotFoundException ignored -> "Auth.deny"; // 인증 요청 거부
            default -> "Auth.fail";    // 시스템 오류
        };

        if(request.getContentType().contains("application/json")) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
	        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
	        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	        SimpleBody body = new SimpleBody(HttpStatus.UNAUTHORIZED.value(), msg);

			objectMapper.writer().writeValue(response.getWriter(), body);

	        return;
		}

		super.onAuthenticationFailure(request, response, exception);
	}
}
