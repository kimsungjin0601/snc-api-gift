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
public class Category extends BaseDomain {

    @Schema(description="표준카테고리_번호")
    private Long categoryNo;

    @Schema(description="상위_표준카테고리_번호 (NULL이면 최상위)")
    private Long parentCategoryNo;

    @Schema(description="파트너_번호")
    private Long partnerNo;

    @Schema(description="표준카테고리_명")
    private String categoryName;

    @Schema(description="카테고리_깊이")
    private Short depth;

    @Schema(description="정렬_순서")
    private Integer sortOrder;

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