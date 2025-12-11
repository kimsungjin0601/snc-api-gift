package com.snc.gift.vo;

import com.cstify.common.base.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class TenantVo extends BaseVo {

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
}
