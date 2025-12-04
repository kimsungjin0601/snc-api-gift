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
public class PointHistory extends BaseDomain {

    @Schema(description="포인트_이력_번호")
    private Long historyNo;

    @Schema(description="파트너_번호")
    private Long partnerNo;

    @Schema(description="상위_파트너_번호(에이전트)")
    private Long parentPartnerNo;

    @Schema(description="주문_번호")
    private Long orderNo;

    @Schema(description="거래_구분_코드")
    private String transTypeCd;

    @Schema(description="거래_금액")
    private Integer transAmt;

    @Schema(description="적용_포인트")
    private Integer point;

    @Schema(description="변경전_포인트")
    private Long pointBefore;

    @Schema(description="변경후_포인트")
    private Long pointAfter;

    @Schema(description="건당_수수료")
    private Integer charge;

    @Schema(description="수수료_대리점")
    private Integer feeDealer;

    @Schema(description="이익금_에이전트")
    private Integer profitsAgent;

    @Schema(description="이익금_본사")
    private Integer profitsHq;

    @Schema(description="메모")
    private String memo;

//    @Schema(description="생성일시")
//    private Date createdAt;
//
//    @Schema(description="생성자")
//    private Long createdBy;
}