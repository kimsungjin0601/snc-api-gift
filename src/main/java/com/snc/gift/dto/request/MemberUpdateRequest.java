package com.snc.gift.dto.request;

import com.cstify.common.base.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateRequest extends BaseVo {

    @Schema(description = "전화번호")
    private String phone;

    @Schema(description = "계좌_정보")
    private String account;

    @Schema(description = "마케팅_수신동의")
    private Boolean isMarketing;
}
