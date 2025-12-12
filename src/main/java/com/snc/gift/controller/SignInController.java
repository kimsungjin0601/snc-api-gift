package com.snc.gift.controller;

import com.snc.gift.dto.request.SignInRequest;
import com.snc.gift.dto.response.TokenResponse;
import com.snc.gift.service.SignInService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "공통-로그인", description = "로그인 관련 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200" , description = "OK"),
        @ApiResponse(responseCode = "401" , description = "USERNAME_NOT_FOUND, BAD_CREDENTIALS"),
        @ApiResponse(responseCode = "500" , description = "Internal Server Error")
})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class SignInController {
    private final SignInService signInService;

    @Operation(summary = "회원_로그인", description = "회원_로그인")
    @PostMapping("/member/sign-in")
    public TokenResponse signInMember(@RequestBody SignInRequest params, HttpServletRequest request, HttpServletResponse response) {
        return signInService.signInMember(params, request,  response);
    }

    @Operation(summary = "일반_관리자_로그인", description = "일반_관리자_로그인")
    @PostMapping("/admin/sign-in")
    public TokenResponse signInAdmin(@RequestBody SignInRequest params, HttpServletRequest request, HttpServletResponse response) {
        return signInService.signInAdmin(params, request,  response);
    }

    @Operation(summary = "주문_관리자_로그인", description = "주문_관리자_로그인")
    @PostMapping("/purchase/sign-in")
    public TokenResponse signInPurchaseAdmin(@RequestBody SignInRequest params, HttpServletRequest request, HttpServletResponse response) {
        return signInService.signInPurchaseAdmin(params, request,  response);
    }
}
