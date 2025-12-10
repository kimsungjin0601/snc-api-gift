package com.snc.gift.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class DenominationCreateDto {
    @Schema(description="상품_코드")
    private String productCode;

    @Schema(description="권종_코드")
    private String denominationCode;

    @Schema(description="수량")
    private Integer qty;
}
