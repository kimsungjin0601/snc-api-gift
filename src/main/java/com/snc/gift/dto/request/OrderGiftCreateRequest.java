package com.snc.gift.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderGiftCreateRequest {
    @Schema(description="구매한 상품권 권종 목록")
    List<DenominationCreateDto> denominationList;
}
