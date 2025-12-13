package com.snc.gift.service;

import com.cstify.common.vo.SecurityUserDetails;
import com.snc.gift.dto.request.SignInRequest;
import com.snc.gift.dto.response.TokenResponse;
import com.snc.gift.security.SecurityUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SignInService {
    private final SecurityUserService securityUserService;
    private final AuthService authService;

    public TokenResponse signInMember(SignInRequest params, HttpServletRequest request, HttpServletResponse response) {
        //checkMemberRole(params.getLoginId());
        return authService.login(params.getLoginId(), params.getPassword(), request, response);
    }

    public TokenResponse signInAdmin(SignInRequest params, HttpServletRequest request, HttpServletResponse response) {
        //checkAdminRole(params.getLoginId());
        return authService.login(params.getLoginId(), params.getPassword(), request, response);
    }

    public TokenResponse signInPurchaseAdmin(SignInRequest params, HttpServletRequest request, HttpServletResponse response) {
        return authService.login(params.getLoginId(), params.getPassword(), request, response);
    }

    private void checkMemberRole(String username){
        SecurityUserDetails userDetails = (SecurityUserDetails) securityUserService.loadUserByUsername(username);
        boolean isMember = userDetails.getAuthorities().stream()
                .anyMatch(a -> Objects.equals(a.getAuthority(), "ROLE_MEMBER"));
        if (!isMember) {
            throw new BadCredentialsException("BAD_CREDENTIALS");
        }
    }

    private void checkAdminRole(String username){
        SecurityUserDetails userDetails = (SecurityUserDetails) securityUserService.loadUserByUsername(username);
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(a ->
                        Objects.equals(a.getAuthority(), "ROLE_AGENT_ADMIN") ||
                                Objects.equals(a.getAuthority(), "ROLE_HQ_ADMIN") ||
                                Objects.equals(a.getAuthority(), "ROLE_SUPER_HQ_ADMIN") ||
                                Objects.equals(a.getAuthority(), "ROLE_SYSTEM_ADMIN")
                );
        if (!isAdmin) {
            throw new BadCredentialsException("BAD_CREDENTIALS");
        }
    }

    private void checkPurchaseAdminRole(String username){
        SecurityUserDetails userDetails = (SecurityUserDetails) securityUserService.loadUserByUsername(username);
        boolean isPurchaseAdmin = userDetails.getAuthorities().stream()
                .anyMatch(a -> Objects.equals(a.getAuthority(), "ROLE_HQ_ORDER"));
        if (!isPurchaseAdmin) {
            throw new BadCredentialsException("BAD_CREDENTIALS");
        }
    }
}
