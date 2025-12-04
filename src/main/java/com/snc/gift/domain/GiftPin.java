package com.snc.gift.domain;

import com.cstify.common.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GiftPin extends BaseDomain {

    @Schema(description="상품권_핀_번호")
    private Long giftPinNo;

    @Schema(description="권종_번호(FK product_denomination)")
    private Long denominationNo;

    @Schema(description="파트너_도메인_번호")
    private Long partnerDomainNo;

    @Schema(description="핀_코드")
    private String pinCode;

    @Schema(description="핀_상태_코드(READY/ASSIGNED/USED/EXPIRED)")
    private String pinStatusCd;

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