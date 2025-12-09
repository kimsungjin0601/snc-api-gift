package com.snc.gift.controller;


import com.cstify.common.annotation.CustomAnnotation.User;
import com.cstify.common.dto.PageRequest;
import com.cstify.common.dto.PageResponse;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.service.OrderGiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "SHOP-주문-상품권", description = "주문-상품권 관련 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200" , description = "OK"),
        @ApiResponse(responseCode = "500" , description = "Internal Server Error")
})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client/order/gift")
public class OrderGiftController {
    private final OrderGiftService orderGiftService;

    @Operation(summary = "주문_내역", description = "주문_내역")
    @GetMapping("/list")
    public PageResponse getOrderList(@Parameter(hidden = true) @User UserInfo userInfo, PageRequest params) {
        return orderGiftService.getOrderList(userInfo, params);
    }

//    @Operation(summary = "주문_생성", description = "주문_생성")
//    @PostMapping("/create")
//    public OrderGiftCreateResponse registerOrder(@Parameter(hidden = true) @User UserInfo userInfo,
//                                                 @RequestBody OrderGiftCreateRequest params) {
//        return orderGiftService.registerOrder(userInfo, params);
//    }
//
//    @Operation(summary = "주문_완료", description = "주문_완료")
//    @GetMapping("/complete/{code}")
//    public OrderGiftCompleteResponse getOrderComplete(@PathVariable("code") String orderCode) {
//        return orderGiftService.getOrderComplete(orderCode);
//    }
}
