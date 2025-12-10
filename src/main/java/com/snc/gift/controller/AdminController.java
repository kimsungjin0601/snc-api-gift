package com.snc.gift.controller;


import com.cstify.common.annotation.CustomAnnotation.User;
import com.cstify.common.dto.PageRequest;
import com.cstify.common.dto.PageResponse;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.dto.response.AdminListDto;
import com.snc.gift.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "ADMIN-관리자", description = "관리자 관련 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200" , description = "OK"),
        @ApiResponse(responseCode = "500" , description = "Internal Server Error")
})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/user")
public class AdminController {
    private final AdminService adminService;

    @Operation(summary = "관리자 목록", description = "관리자 목록")
    @GetMapping("")
    public PageResponse<AdminListDto> getAdminList(@Parameter(hidden = true) @User UserInfo userInfo, PageRequest params) {
        return adminService.getAdminList(userInfo, params);
    }
}
