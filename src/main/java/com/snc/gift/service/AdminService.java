package com.snc.gift.service;

import com.cstify.common.dto.PageDto;
import com.cstify.common.dto.PageRequest;
import com.cstify.common.dto.PageResponse;
import com.cstify.common.util.ResponseUtil;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.dto.mapper.AdminDtoMapper;
import com.snc.gift.dto.search.AdminSearch;
import com.snc.gift.mapper.AdminMapper;
import com.snc.gift.vo.AdminVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminDtoMapper adminDtoMapper;
    private final AdminMapper adminMapper;

    public PageResponse getAdminList(UserInfo userInfo, PageRequest params){
        AdminSearch search = adminDtoMapper.toAdminSearch(params, userInfo);  // 검색_DTO
        Integer totCnt = adminMapper.getAdminCount(search);         // 건수_조회
        List<AdminVo> list = adminMapper.getAdminList(search);      // 목록_조회
        return ResponseUtil.getPageResponse(PageDto.of(params, totCnt), adminDtoMapper.toAdminDtoList(list));
    }

}
