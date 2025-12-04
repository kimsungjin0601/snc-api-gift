package com.snc.gift.domain;

import com.cstify.common.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GiftPinAssignment extends BaseDomain {

    @Schema(description="상품권_핀_할당번호")
    private Long assignmentNo;

    @Schema(description="상품권_핀_번호")
    private Long giftPinNo;

    @Schema(description="매핑된_주문_아이템_번호")
    private Long orderItemNo;

    @Schema(description="교환_사용자_번호")
    private Long userNo;

    @Schema(description="할당_일시")
    private Date assignedAt;

    @Schema(description="비할당_일시")
    private Date unassignedAt;

    @Schema(description="메모")
    private String remark;

//    @Schema(description="생성자")
//    private Long createdBy;
}