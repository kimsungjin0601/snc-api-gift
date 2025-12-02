package com.snc.gift.domain;

import com.cstify.common.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends BaseDomain {

    @Schema(description="관리자_번호")
    private Long adminNo;

    @Schema(description="사용자_번호")
    private Long userNo;

    @Schema(description="텔레그램_채널_ID")
    private String telegramId;

    @Schema(description="소속_에이전트_번호")
    private Long agentNo;

    @Schema(description="건당_수수료")
    private Integer charge;

    @Schema(description="수수료율")
    private BigDecimal feeRate;

    @Schema(description="정산은행")
    private String bankName;

    @Schema(description="정산계좌")
    private String account;

    @Schema(description="보유_포인트")
    private Long ownPoint;

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