package com.snc.gift.mapper;

import com.snc.gift.vo.GiftVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GiftMapper {
    List<GiftVo> getGiftMainList(@Param("s3Domain") String s3Domain);

    GiftVo getGiftDetail(@Param("productCode") String productCode, @Param("s3Domain") String s3Domain);

    List<GiftVo> getGiftDenominationList(@Param("productNo") Long productNo);

    GiftVo getGiftDenominationByCode(@Param("denominationCode") String denominationCode);

}
