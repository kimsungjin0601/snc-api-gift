package com.snc.gift.controller;

import com.snc.gift.dto.response.GiftDetailResponse;
import com.snc.gift.service.GiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "상품권", description = "상품권 관련 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200" , description = "OK"),
        @ApiResponse(responseCode = "500" , description = "Internal Server Error")
})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client/gift")
public class GiftController {
    private final GiftService giftService;

    @Operation(summary = "상품권_상세", description = "상품권_상세")
    @GetMapping("/{productCode}")
    public GiftDetailResponse getGiftDetail(@PathVariable String productCode) {
        return giftService.getGiftDetail(productCode);
    }
}
