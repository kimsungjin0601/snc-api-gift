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
public class PaymentStatusHistory extends BaseDomain {

    @Schema(description="이력번호")
    private Long historyNo;

    @Schema(description="결제번호")
    private Long paymentNo;

    @Schema(description="이전 결제 상태")
    private String prevStatusCd;

    @Schema(description="결제 상태")
    private String statusCd;

    @Schema(description="상태 변경 관련 메모")
    private String remark;

//    @Schema(description="생성일시")
//    private Date createdAt;
//
//    @Schema(description="생성자")
//    private Long createdBy;
}