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

    @Schema(description="파트너_번호")
    private Long partnerNo;

    @Schema(description="상위_파트너_번호(에이전트)")
    private Long parentPartnerNo;

    @Schema(description="사용자_번호")
    private Long userNo;


    @Schema(description="주문_코드(외부노출용)")
    private String orderCode;

    @Schema(description="주문상태_코드")
    private String orderStatusCd;

    @Schema(description="가상핀_코드")
    private String vPinCode;

    @Schema(description="주문_일시")
    private Instant orderAt;

    @Schema(description="결제_완료일시")
    private Instant paidAt;

    @Schema(description="가상핀_발송_완료일시")
    private Instant completedAt;

    @Schema(description="주문_취소일시")
    private Instant cancelAt;

    @Schema(description="총_결제_금액")
    private Integer totalAmount;

    @Schema(description="총_할인_금액")
    private Integer discountAmount;

    @Schema(description="최종_결제_금액")
    private Integer finalAmount;

    @Schema(description="주문자_명")
    private String ordererName;

    @Schema(description="주문자_전화")
    private String ordererPhone;

    @Schema(description="주문자_이메일")
    private String ordererEmail;

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