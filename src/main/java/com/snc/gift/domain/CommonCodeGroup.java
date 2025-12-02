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
public class CommonCodeGroup extends BaseDomain {

    @Schema(description="그룹_코드")
    private String codeGroup;

    @Schema(description="그룹_명")
    private String groupName;

    @Schema(description="설명")
    private String description;

//    @Schema(description="삭제여부")
//    private Boolean isDeleted;
//
//    @Schema(description="수정일시")
//    private Date updatedAt;
//
//    @Schema(description="수정자")
//    private Long updatedBy;
}