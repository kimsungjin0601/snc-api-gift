package com.snc.gift.dto.search;

import com.cstify.common.base.BaseSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@SuperBuilder
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class OrderGiftUserSearch extends BaseSearch {
    @Serial
    private static final long serialVersionUID = -4531833874688336927L;

    @Schema(description="사용자_번호")
    private Long userNo;
}
