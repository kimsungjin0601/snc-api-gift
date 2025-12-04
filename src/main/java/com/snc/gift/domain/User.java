package com.snc.gift.domain;

import com.cstify.common.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseDomain {

    @Schema(description="사용자_번호")
    private Long userNo;

    @Schema(description="파트너_번호")
    private Long partnerNo;

    @Schema(description="로그인ID")
    private String loginId;

    @Schema(description="패스워드")
    private String password;

    @Schema(description="사용자명")
    private String userName;

    @Schema(description="전화번호")
    private String phone;

    @Schema(description="이메일")
    private String email;

    @Schema(description="텔레그램_채널_ID")
    private String telegramId;

    @Schema(description="사용자등급(회원/대리점/에이전트/일반관리자/주문관리자)")
    private String userGrade;

    @Schema(description="사용자_상태(ACTIVE,INACTIVE)")
    private String userStatusCd;

//    @Schema(description="삭제여부")
//    private Boolean isDeleted;
//
//    @Schema(description="생성일시")
//    private Date createdAt;
//
//    @Schema(description="생성자")
//    private Long createdBy;
//
//    @Schema(description="수정일시")
//    private Date updatedAt;
//
//    @Schema(description="수정자")
//    private Long updatedBy;
}