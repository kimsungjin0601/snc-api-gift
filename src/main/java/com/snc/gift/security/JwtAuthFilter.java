package com.snc.gift.security;

import com.cstify.common.exception.TokenBlacklistException;
import com.cstify.common.exception.TokenExpiredException;
import com.cstify.common.exception.TokenInvalidException;
import com.cstify.common.provider.TokenProvider;
import com.cstify.common.service.SecurityUserDetailsService;
import com.cstify.common.service.TokenBlockService;
import com.cstify.common.util.RequestUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final SecurityUserDetailsService userDetailsService;
    private final TokenBlockService tokenBlockService;
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(@NonNull final HttpServletRequest request,
                                    @NonNull final HttpServletResponse response,
                                    @NonNull final FilterChain filterChain) throws ServletException, IOException {
        try {
            String accessToken = RequestUtil.getAuthorizationToken(request);
            if (accessToken == null) {
                filterChain.doFilter(request, response);
                return;
            }

            tokenProvider.validateToken(accessToken);
            tokenBlockService.isBlocked(accessToken);

            Long userNo = tokenProvider.getUserNo(accessToken);
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails user = userDetailsService.loadUserByUserNo(userNo);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
//                log.info("Authenticated user: {}, IP: {}", user.getUsername(), RequestUtil.getClientIP(request));
            }
        } catch (TokenExpiredException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"code\":801,\"message\":\"ACCESS_TOKEN_EXPIRED\"}");
            return;
        } catch (TokenInvalidException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"code\":802,\"message\":\"ACCESS_TOKEN_INVALID\"}");
            return;
        } catch (TokenBlacklistException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"code\":803,\"message\":\"TOKEN_BLACKLISTED\"}");
            return;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"code\":500,\"message\":\"INTERNAL_SERVER_ERROR\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
