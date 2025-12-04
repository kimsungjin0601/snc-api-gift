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
public class OrderPartner extends BaseDomain  {

    @Schema(description="주문_번호")
    private Long orderNo;

    @Schema(description="파트너_번호")
    private Long partnerNo;

    @Schema(description="상위_파트너_번호(에이전트)")
    private Long parentPartnerNo;

    @Schema(description="주문_코드")
    private String orderCode;

    @Schema(description="주문_상태_코드")
    private String orderStatusCd;

    @Schema(description="주문완료일시")
    private Instant orderAt;

    @Schema(description="배송완료일시")
    private Instant completedAt;

    @Schema(description="취소일시")
    private Instant cancelAt;

    @Schema(description="주문_금액")
    private Integer orderAmount;

    @Schema(description="메모 (주문_상세)")
    private String memo;

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