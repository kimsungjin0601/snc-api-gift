package com.snc.gift.domain;

import com.cstify.common.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderGiftItem extends BaseDomain {

    @Schema(description="상품권_주문_아이템_번호")
    private Long orderItemNo;

    @Schema(description="상품권_주문_번호(FK)")
    private Long orderNo;

    @Schema(description="상품_번호")
    private Long productNo;

    @Schema(description="권종_번호")
    private Long denominationNo;

    @Schema(description="파트너_도메인_번호")
    private Long partnerDomainNo;

    @Schema(description="상품명")
    private String productName;

    @Schema(description="수량")
    private Integer qty;

    @Schema(description="단가")
    private Integer unitPrice;

    @Schema(description="차감_금액")
    private Integer discountPrice;

    @Schema(description="최종_금액")
    private Integer finalPrice;

    @Schema(description="유효기간")
    private Instant expirationDate;

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