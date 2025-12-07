package com.snc.gift.controller;


import com.snc.gift.dto.request.SignRequest;
import com.snc.gift.dto.response.TokenResponse;
import com.snc.gift.service.SignUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "서비스 가입", description = "서비스 가입 관련 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200" , description = "OK"),
        @ApiResponse(responseCode = "500" , description = "Internal Server Error")
})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class SignUpController {
    private final SignUpService signUpService;

    @Operation(summary = "회원 가입", description = "회원 가입")
    @PostMapping("/sign-up")
    public void signup(@RequestBody SignRequest params, HttpServletRequest request, HttpServletResponse response) {
        signUpService.signup(params, request, response);
    }
}
