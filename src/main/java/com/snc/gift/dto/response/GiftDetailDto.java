package com.snc.gift.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class GiftDetailDto {

    @Schema(description="상품번호")
    private Long productNo;

    @Schema(description="상품코드")
    private String productCode;

    @Schema(description="상품명")
    private String productName;

    @Schema(description="이미지_URL")
    private String imageUrl;
}
