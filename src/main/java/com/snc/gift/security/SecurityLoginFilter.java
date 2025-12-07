package com.snc.gift.security;

import com.cstify.common.vo.SecurityUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snc.gift.vo.LoginInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class SecurityLoginFilter extends UsernamePasswordAuthenticationFilter {

	private final ObjectMapper objectMapper;

    private final SecurityUserService securityUserService;

	private boolean postOnly = true;

	private LoginInfo loginInfo = null;

	@Override
    @NullMarked
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		if (this.postOnly && !HttpMethod.POST.name().equals(request.getMethod())) {
            if(log.isDebugEnabled()) {
                log.debug("Authentication method not supported. Request method: {}", request.getMethod());
            }
            throw new AuthenticationServiceException("Authentication method not supported");
        }
		
		if (request.getContentType().contains("application/json") && StringUtils.isEmpty(super.obtainUsername(request))) {
			try {
                this.loginInfo = objectMapper.readValue(request.getReader(), LoginInfo.class);
                log.debug("Settings login parameters from json reader, {}", this.loginInfo);
            } catch (IOException e) {
                throw new AuthenticationServiceException("Authentication parsing error");
            }
		}
		
		String username = this.obtainUsername(request);
		String password = this.obtainPassword(request);
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new AuthenticationServiceException("username or password is not valid.");
        }

        SecurityUserDetails user = (SecurityUserDetails) securityUserService.loadUserByUsername(username);
        if ("/api/auth/sign-in".equals(request.getRequestURI())) {
            boolean isMember = user.getAuthorities().stream()
                    .anyMatch(a -> Objects.equals(a.getAuthority(), "ROLE_MEMBER"));
            if (!isMember) {
                throw new BadCredentialsException("BAD_CREDENTIALS");
            }
        } else if ("/api/auth/sign-in/admin".equals(request.getRequestURI())) {
            boolean isAdmin = user.getAuthorities().stream()
                    .anyMatch(a ->
                            Objects.equals(a.getAuthority(), "ROLE_AGENT_ADMIN") ||
                            Objects.equals(a.getAuthority(), "ROLE_HQ_ADMIN") ||
                            Objects.equals(a.getAuthority(), "ROLE_SUPER_HQ_ADMIN")
                    );
            if (!isAdmin) {
                throw new BadCredentialsException("BAD_CREDENTIALS");
            }
        } else if ("/api/auth/sign-in/order".equals(request.getRequestURI())) {
            boolean isHQOrder = user.getAuthorities().stream()
                    .anyMatch(a -> Objects.equals(a.getAuthority(), "ROLE_HQ_ORDER"));
            if (!isHQOrder) {
                throw new BadCredentialsException("BAD_CREDENTIALS");
            }
        }

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		return this.getAuthenticationManager().authenticate(token);
	}

	@Override
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	@Override
	protected String obtainUsername(@NonNull HttpServletRequest request) {
		String username = super.obtainUsername(request);
		if (StringUtils.isEmpty(username) && loginInfo != null) {
            username = loginInfo.getLoginId();
        }
        return username;
	}

	@Override
	protected String obtainPassword(@NonNull HttpServletRequest request) {
		String password = super.obtainPassword(request);
		if (StringUtils.isEmpty(password) && loginInfo != null) {
            password = loginInfo.getPassword();
        }
		return password;
	}
}
