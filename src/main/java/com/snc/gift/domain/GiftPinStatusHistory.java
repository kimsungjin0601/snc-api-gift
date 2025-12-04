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
public class GiftPinStatusHistory extends BaseDomain {

    @Schema(description="이력번호")
    private Long historyNo;

    @Schema(description="상품권_핀_번호")
    private Long giftPinNo;

    @Schema(description="이전_핀_상태_코드")
    private String prevStatusCd;

    @Schema(description="핀_상태_코드")
    private String statusCd;

    @Schema(description="상태_변경_관련_메모")
    private String remark;

//    @Schema(description="생성일시")
//    private Date createdAt;
//
//    @Schema(description="생성자")
//    private Long createdBy;
}