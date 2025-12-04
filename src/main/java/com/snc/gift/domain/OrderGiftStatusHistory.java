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
public class OrderGiftStatusHistory extends BaseDomain {

    @Schema(description="이력_번호")
    private Long historyNo;

    @Schema(description="주문_번호")
    private Long orderNo;

    @Schema(description="이전_주문_상태_코드")
    private String prevStatusCd;

    @Schema(description="주문_상태_코드")
    private String statusCd;

    @Schema(description="상태_변경_관련_메모")
    private String remark;

//    @Schema(description="생성일시")
//    private Date createdAt;
//
//    @Schema(description="생성자")
//    private Long createdBy;
}