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
public class Tenant extends BaseDomain {

    @Schema(description="테넌트_번호")
    private Long tenantNo;

    @Schema(description="테넌트_코드(외부식별자)")
    private String tenantCode;

    @Schema(description="테넌트_명")
    private String tenantName;

    @Schema(description="기본_타임존")
    private String timezone;

    @Schema(description="기본_로케일")
    private String locale;

    @Schema(description="통화_코드")
    private String currencyCode;

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