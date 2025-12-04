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
public class Product extends BaseDomain {

    @Schema(description="상품번호")
    private Long productNo;

    @Schema(description="파트너_번호")
    private Long partnerNo;

    @Schema(description="판매자번호")
    private Long sellerNo;

    @Schema(description="표준카테고리번호")
    private Long categoryNo;

    @Schema(description="상품명")
    private String productName;

    @Schema(description="상품코드")
    private String productCode;

    @Schema(description="상품상태코드 (판매중,품절,판매중지)")
    private String productStatusCd;

    @Schema(description="전시여부")
    private Boolean isDisplayed;

    @Schema(description="전시우선순위")
    private Integer displayRank;

    @Schema(description="판매시작일시")
    private Instant startAt;

    @Schema(description="판매종료일시")
    private Instant endAt;

    @Schema(description="성인상품여부")
    private Boolean isAdult;

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