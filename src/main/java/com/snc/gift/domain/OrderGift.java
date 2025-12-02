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
public class OrderGift extends BaseDomain {

    @Schema(description="주문_번호")
    private Long orderNo;

    @Schema(description="회원_번호")
    private Long userNo;

    @Schema(description="지점_번호(admin_no)")
    private Long branchNo;

    @Schema(description="에이전트_번호(admin_no)")
    private Long agentNo;

    @Schema(description="주문_코드")
    private String orderCode;

    @Schema(description="주문상태_코드")
    private String orderStatusCd;

    @Schema(description="상품_번호")
    private Long productNo;

    @Schema(description="핀_번호")
    private Long pinNo;

    @Schema(description="주문_금액")
    private Integer orderAmt;

    @Schema(description="주문_일시")
    private Instant orderAt;

    @Schema(description="입금일시")
    private Instant paidAt;

    @Schema(description="주문_취소일시")
    private Instant cancelAt;

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