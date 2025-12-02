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
public class ProductImage extends BaseDomain {

    @Schema(description="상품_이미지_번호")
    private Long productImageNo;

    @Schema(description="상품번호")
    private Long productNo;

    @Schema(description="이미지_유형 (main, list, detail, zoom, etc)")
    private String imageTypeCd;

    @Schema(description="이미지_URL")
    private String imageUrl;

    @Schema(description="정렬순서")
    private Integer sortOrder;

    @Schema(description="이미지_대체_텍스트")
    private String altText;

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