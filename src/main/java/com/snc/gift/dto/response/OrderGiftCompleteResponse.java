package com.snc.gift.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@SuperBuilder
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderGiftCompleteResponse {

    @Schema(description="주문_코드(외부노출용)")
    private String orderCode;

    @Schema(description="대표_상품명")
    private String mainProductName;

    @Schema(description="주문_일시")
    private Instant orderAt;

    @Schema(description="주문상태_명")
    private String orderStatusName;

    @Schema(description="최종_결제_금액")
    private Integer finalAmount;

    @Schema(description="주문자_명")
    private String ordererName;

    @Schema(description="입금_계좌")
    private String depositAccount;
}
