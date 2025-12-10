package com.snc.gift.vo;

import com.cstify.common.base.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class PartnerVo extends BaseVo {

    @Schema(description="테넌트_번호")
    private Long tenantNo;

    @Schema(description="파트너_번호")
    private Long partnerNo;

    @Schema(description="파트너_코드")
    private String partnerCode;

    @Schema(description="상위_파트너_번호")
    private Long parentPartnerNo;

    @Schema(description="도메인_번호")
    private Long partnerDomainNo;
}
