package com.snc.gift.service;

import com.snc.gift.dto.mapper.GiftDtoMapper;
import com.snc.gift.dto.response.MainResponse;
import com.snc.gift.mapper.GiftMapper;
import com.snc.gift.vo.GiftVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    @Value("${aws.s3.domain}")
    String s3Domain;

    private final GiftDtoMapper giftDtoMapper;
    private final GiftMapper giftMapper;

    public MainResponse getMainInfo(){
        List<GiftVo> giftList = giftMapper.getGiftMainList(s3Domain);
        return MainResponse.builder().giftList(giftDtoMapper.toGiftMainDtoList(giftList)).build();
    }
}
