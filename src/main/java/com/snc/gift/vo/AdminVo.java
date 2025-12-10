package com.snc.gift.vo;

import com.cstify.common.base.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@SuperBuilder
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminVo extends BaseVo {

    @Schema(description="회원_번호")
    private Long memberNo;

    @Schema(description="사용자_번호")
    private Long userNo;

    @Schema(description="사용자명")
    private String userName;

    @Schema(description="전화번호")
    private String phone;

    @Schema(description="보유_포인트")
    private Integer ownPoint;

    @Schema(description="주문_횟수")
    private Integer orderCnt;

    @Schema(description="주문_금액")
    private Integer orderAmount;

    @Schema(description="계좌_정보")
    private String account;

    @Schema(description="마케팅_수신동의")
    private Boolean isMarketing;

    @Schema(description="가입_일시")
    private Instant joinedAt;

    @Schema(description="탈퇴_일시")
    private Instant withdrawnAt;
}
