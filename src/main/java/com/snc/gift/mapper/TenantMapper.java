package com.snc.gift.mapper;

import com.snc.gift.vo.TenantVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TenantMapper {
    TenantVo getTenantByDefault();
}
