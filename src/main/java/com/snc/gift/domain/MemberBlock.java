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
public class MemberBlock extends BaseDomain  {

    @Schema(description="차단_번호")
    private Long blockNo;

    @Schema(description="차단_회원_번호")
    private Long memberNo;

    @Schema(description="차단한_사용자_번호")
    private Long blockerUserNo;

    @Schema(description="차단_사유_코드")
    private String reasonCd;

    @Schema(description="차단_사유")
    private String reasonText;

    @Schema(description="차단_활성_여부")
    private Boolean isActive;

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