package com.snc.gift.vo;

import com.cstify.common.base.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class GiftVo extends BaseVo {

    @Schema(description="상품번호")
    private Long productNo;

    @Schema(description="상품코드")
    private String productCode;

    @Schema(description="상품명")
    private String productName;

    @Schema(description="권종_번호")
    private Long denominationNo;

    @Schema(description="권종_코드")
    private String denominationCode;

    @Schema(description="권종_명")
    private String denominationName;

    @Schema(description="권종_금액")
    private Integer amount;

    @Schema(description="판매가(소비자_가격)")
    private Integer salePrice;

    @Schema(description="할인율")
    private Float discountRate;

    @Schema(description="개당_할인가")
    private Integer discountPrice;

    @Schema(description="이미지_URL")
    private String imageUrl;
}
