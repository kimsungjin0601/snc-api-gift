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
public class ProductDenomination extends BaseDomain  {

    @Schema(description="권종_번호")
    private Long denominationNo;

    @Schema(description="상품_번호")
    private Long productNo;

    @Schema(description="권종_코드")
    private String denominationCode;

    @Schema(description="권종_명")
    private String denominationName;

    @Schema(description="권종_금액")
    private Integer amount;

    @Schema(description="판매가(소비자_가격)")
    private Integer salePrice;

    @Schema(description="공급가(정산CP_단가)")
    private Integer supplyPrice;

    @Schema(description="PIN_재고_방식(API,POOL,FILE)")
    private String stockTypeCd;

    @Schema(description="일_최대_판매_수량")
    private Integer maxDailyQty;

    @Schema(description="할인율")
    private Float discountRate;

    @Schema(description="할인가")
    private Integer discountPrice;

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