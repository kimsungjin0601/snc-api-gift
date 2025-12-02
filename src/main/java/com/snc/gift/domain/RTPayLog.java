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
public class RTPayLog extends BaseDomain {

    @Schema(description="로그_번호")
    private Long logNo;

    @Schema(description="입금은행")
    private String bankName;

    @Schema(description="입금자명")
    private String userName;

    @Schema(description="입금금액")
    private Integer paidAmt;

    @Schema(description="입금일시")
    private Instant paidAt;

    @Schema(description="전송_데이터_전문")
    private String transData;
}