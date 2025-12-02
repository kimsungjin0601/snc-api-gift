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
public class CommonCode extends BaseDomain {

    @Schema(description="코드_명")
    private String codeName;

    @Schema(description="설명")
    private String description;

    @Schema(description="정렬순서")
    private Integer sortOrder;

//    @Schema(description="삭제여부")
//    private Boolean isDeleted;
//
//    @Schema(description="수정일시")
//    private Date updatedAt;
//
//    @Schema(description="수정자")
//    private Long updatedBy;
}