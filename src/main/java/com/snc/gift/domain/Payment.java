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
public class Payment extends BaseDomain {

    @Schema(description="결제_번호")
    private Long paymentNo;

    @Schema(description="주문번호")
    private Long orderNo;

    @Schema(description="주문_타입_코드(NORMAL, GIFT)")
    private String orderTypeCd;

    @Schema(description="결제_수단_코드")
    private String paymentMethodCd;

    @Schema(description="결제_상태_코드")
    private String paymentStatusCd;

    @Schema(description="결제_금액")
    private Integer paidAmount;

    @Schema(description="결제_완료_일시")
    private Date paidAt;

    @Schema(description="PG사_거래_고유번호")
    private String tid;

//    @Schema(description="생성일시")
//    private Date createdAt;
//
//    @Schema(description="수정일시")
//    private Date updatedAt;
}