package com.snc.gift.dto.response;

import com.cstify.common.base.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class GiftDetailResponse extends BaseVo {

    @Schema(description="상품권 정보")
    private GiftDetailDto giftInfo;

    @Schema(description="권종 목록")
    private List<DenominationDto> denominationList;
}
