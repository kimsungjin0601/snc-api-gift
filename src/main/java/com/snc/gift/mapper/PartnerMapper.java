package com.snc.gift.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PartnerMapper {
    Long getPartnerNoByDomain(@Param("domainName") String domainName);
}
