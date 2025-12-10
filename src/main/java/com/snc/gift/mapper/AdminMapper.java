package com.snc.gift.mapper;

import com.snc.gift.dto.search.AdminSearch;
import com.snc.gift.vo.AdminVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    Integer getAdminCount(AdminSearch params);
    List<AdminVo> getAdminList(AdminSearch params);
}
