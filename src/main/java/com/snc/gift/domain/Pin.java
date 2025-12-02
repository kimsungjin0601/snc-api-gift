package com.snc.gift.domain;

import com.cstify.common.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Pin extends BaseDomain {

    @Schema(description="핀_번호")
    private Long pinNo;

    @Schema(description="핀_코드")
    private String pinCode;

    @Schema(description="핀_상태_코드")
    private String pinStatusCd;

    @Schema(description="발행일시")
    private Instant issuedAt;

    @Schema(description="전송일시")
    private Instant sentAt;

    @Schema(description="사용일시")
    private Instant usedAt;

    @Schema(description="만료일시")
    private Instant expireAt;

//    @Schema(description="생성일시")
//    private Date createdAt;
//
//    @Schema(description="수정일시")
//    private Date updatedAt;
}