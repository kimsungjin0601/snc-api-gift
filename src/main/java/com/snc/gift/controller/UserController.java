package com.snc.gift.controller;

import com.cstify.common.annotation.CustomAnnotation.User;
import com.cstify.common.vo.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "공통-사용자", description = "사용자 관련 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200" , description = "OK"),
        @ApiResponse(responseCode = "500" , description = "Internal Server Error")
})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Operation(summary = "로그인 한 사용자 정보", description = "로그인 한 사용자 정보")
    @GetMapping("/me")
    public UserInfo getLoginUserInfo(@Parameter(hidden = true) @User UserInfo userInfo) {
        return userInfo;
    }
}
