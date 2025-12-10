package com.snc.gift.domain;

import com.cstify.common.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Partner extends BaseDomain {

    @Schema(description="파트너_번호")
    private Long partnerNo;

    @Schema(description="상위_파트너_번호")
    private Long parentPartnerNo;

    @Schema(description="테넌트_번호")
    private Long tenantNo;

    @Schema(description="파트너_유형코드")
    private String partnerTypeCd;

    @Schema(description="파트너_코드(외부식별자)")
    private String partnerCode;

    @Schema(description="파트너_명")
    private String partnerName;

    @Schema(description="담당자_이메일")
    private String contactEmail;

    @Schema(description="담당자_전화번호")
    private String contactPhone;

    @Schema(description="텔레그램_채널_ID")
    private String telegramId;

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

//    @Schema(description="삭제여부")
//    private Boolean isDeleted;
//
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