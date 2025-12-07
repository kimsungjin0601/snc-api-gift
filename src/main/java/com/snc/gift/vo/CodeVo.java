package com.snc.gift.vo;

import com.cstify.common.base.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class CodeVo extends BaseVo {

    @Schema(description = "코드")
    private String code;

    @Schema(description = "코드명")
    private String name;
}
