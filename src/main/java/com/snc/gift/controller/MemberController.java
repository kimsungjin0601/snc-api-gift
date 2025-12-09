package com.snc.gift.controller;


import com.cstify.common.annotation.CustomAnnotation.User;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.dto.request.MemberUpdateRequest;
import com.snc.gift.dto.response.MemberInfoResponse;
import com.snc.gift.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "SHOP-회원", description = "회원 관련 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200" , description = "OK"),
        @ApiResponse(responseCode = "500" , description = "Internal Server Error")
})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원_정보", description = "회원_정보")
    @GetMapping("/info")
    public MemberInfoResponse getMemberInfo(@Parameter(hidden = true)  @User UserInfo userInfo) {
        return memberService.getMemberInfo(userInfo);
    }

    @Operation(summary = "회원_정보_수정", description = "회원_정보_수정")
    @PatchMapping("/info")
    public void updateMemberByMe(@RequestBody MemberUpdateRequest params) {
        memberService.updateMemberByMe(params);
    }
}
