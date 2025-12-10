package com.snc.gift.mapper;

import com.snc.gift.vo.PartnerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PartnerMapper {
    Long getPartnerNoByDomain(@Param("domainName") String domainName);

    PartnerVo getUserPartner(@Param("userNo") Long userNo);
}
