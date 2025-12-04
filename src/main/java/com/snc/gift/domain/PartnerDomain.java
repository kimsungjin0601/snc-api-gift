package com.snc.gift.domain;

import com.cstify.common.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDomain extends BaseDomain  {

    @Schema(description="도메인_번호")
    private Long partnerDomainNo;

    @Schema(description="파트너_번호")
    private Long partnerNo;

    @Schema(description="도메인_명")
    private String domainName;

    @Schema(description="입금_계좌")
    private String depositAccount;

    @Schema(description="텔레그램_채널_ID")
    private String telegramId;

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