package com.snc.gift.domain;

import com.cstify.common.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseDomain {

    @Schema(description="회원_번호")
    private Long memberNo;

    @Schema(description="사용자_번호")
    private Long userNo;

    @Schema(description="보유_포인트")
    private Integer ownPoint;

    @Schema(description="주문_횟수")
    private Integer orderCnt;

    @Schema(description="주문_금액")
    private Integer orderAmount;

    @Schema(description="가입_일시")
    private Instant joinedAt;

    @Schema(description="탈퇴_일시")
    private Instant withdrawnAt;

//    @Schema(description="삭제여부")
//    private Boolean isDeleted;
//
//    @Schema(description="수정일시")
//    private Date updatedAt;
//
//    @Schema(description="수정자")
//    private Long updatedBy;
}