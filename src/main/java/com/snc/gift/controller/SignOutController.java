package com.snc.gift.controller;


import com.cstify.common.annotation.CustomAnnotation.User;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.service.SignOutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "공통-로그 아웃", description = "로그 아웃 관련 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200" , description = "OK"),
        @ApiResponse(responseCode = "500" , description = "Internal Server Error")
})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class SignOutController {
    private final SignOutService signOutService;

    @Operation(summary = "로그 아웃", description = "로그 아웃")
    @PostMapping("/sign-out")
    public void signOut(@Parameter(hidden = true) @User UserInfo userInfo, HttpServletRequest request) {
        signOutService.signOut(userInfo, request);
    }

    @Operation(summary = "강제 로그 아웃", description = "강제 로그 아웃")
    @PostMapping("/sign-out/force/{userNo}")
    public void signOut(@PathVariable Long userNo) {
        signOutService.signOut(userNo);
    }
}
