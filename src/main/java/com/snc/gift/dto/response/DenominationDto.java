package com.snc.gift.dto.response;

import com.cstify.common.base.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class DenominationDto extends BaseVo {

    @Schema(description="권종_번호")
    private Long denominationNo;

    @Schema(description="권종_코드")
    private String denominationCode;

    @Schema(description="권종_명")
    private String denominationName;

    @Schema(description="권종_금액")
    private Integer amount;

    @Schema(description="판매가(소비자_가격)")
    private Integer salePrice;
}
