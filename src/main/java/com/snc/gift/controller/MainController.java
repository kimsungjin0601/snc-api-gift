package com.snc.gift.controller;

import com.snc.gift.dto.response.MainResponse;
import com.snc.gift.service.MainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "SHOP-메인", description = "메인 관련 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200" , description = "OK"),
        @ApiResponse(responseCode = "500" , description = "Internal Server Error")
})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client/main")
public class MainController {
    private final MainService mainService;

    @Operation(summary = "메인_정보", description = "메인_정보")
    @GetMapping("")
    public MainResponse getMainInfo() {
        return mainService.getMainInfo();
    }
}
