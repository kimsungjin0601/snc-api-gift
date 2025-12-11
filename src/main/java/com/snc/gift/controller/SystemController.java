package com.snc.gift.controller;

import com.snc.gift.dto.request.AdminCreateRequest;
import com.snc.gift.service.SystemService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Hidden
@Tag(name = "SYSTEM-관련", description = "SYSTEM 관련 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200" , description = "OK"),
        @ApiResponse(responseCode = "500" , description = "Internal Server Error")
})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/system")
public class SystemController {
    private final SystemService systemService;

    @Operation(summary = "시스템_관리자_등록", description = "시스템_관리자_등록")
    @PostMapping("/admin")
    public void createAdmin(@RequestBody AdminCreateRequest params) {
        systemService.createSystemAdmin(params);
    }
}
