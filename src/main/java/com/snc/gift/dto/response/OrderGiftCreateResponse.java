package com.snc.gift.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderGiftCreateResponse {
    @Schema(description="주문_코드")
    private String orderCode;
}
