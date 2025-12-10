package com.snc.gift.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@SuperBuilder
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminListDto {
    @Schema(description="파트너_번호")
    private Long partnerNo;

    @Schema(description="사용자_번호")
    private Long userNo;

    @Schema(description="로그인_ID")
    private String loginId;

    @Schema(description="사용자명")
    private String userName;

    @Schema(description="회원_번호")
    private Long memberNo;

    @Schema(description="텔레그램_채널_ID")
    private String telegramId;

    @Schema(description="파트너_유형명")
    private String partnerTypeName;

    @Schema(description="상위_파트너_명")
    private String parentPartnerName;

    @Schema(description="보유_포인트")
    private Long ownPoint;

    @Schema(description="건당_수수료")
    private Integer charge;

    @Schema(description="생성일시")
    private Instant createdAt;

    @Schema(description="사용자_상태_명")
    private String userStatusName;
}
