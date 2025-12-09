package com.snc.gift.service;

import com.snc.gift.dto.mapper.GiftDtoMapper;
import com.snc.gift.dto.response.GiftDetailResponse;
import com.snc.gift.mapper.GiftMapper;
import com.snc.gift.vo.GiftVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftService {

    @Value("${aws.s3.domain}")
    String s3Domain;

    private final GiftDtoMapper giftDtoMapper;
    private final GiftMapper giftMapper;

    public GiftDetailResponse getGiftDetail(Long productNo) {
        GiftVo giftDetail = giftMapper.getGiftDetail(productNo, s3Domain);
        List<GiftVo> denominationList = giftMapper.getGiftDenominationList(productNo);

        return GiftDetailResponse.builder()
                .giftInfo(giftDtoMapper.toGiftDetailDto(giftDetail))
                .denominationList(giftDtoMapper.toDenominationDtoList(denominationList))
                .build();
    }
}
