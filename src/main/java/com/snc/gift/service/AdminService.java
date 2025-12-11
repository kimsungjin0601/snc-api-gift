package com.snc.gift.service;

import com.cstify.common.dto.PageDto;
import com.cstify.common.dto.PageRequest;
import com.cstify.common.dto.PageResponse;
import com.cstify.common.util.CodeUtil;
import com.cstify.common.util.ResponseUtil;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.domain.Partner;
import com.snc.gift.domain.User;
import com.snc.gift.domain.UserPartner;
import com.snc.gift.dto.mapper.AdminDtoMapper;
import com.snc.gift.dto.request.AdminCreateRequest;
import com.snc.gift.dto.response.AdminListDto;
import com.snc.gift.dto.search.AdminSearch;
import com.snc.gift.mapper.AdminMapper;
import com.snc.gift.mapper.PartnerMapper;
import com.snc.gift.mapper.UserMapper;
import com.snc.gift.type.UserStatusType;
import com.snc.gift.vo.AdminVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminDtoMapper adminDtoMapper;
    private final AdminMapper adminMapper;
    private final UserMapper userMapper;
    private final PartnerMapper partnerMapper;

    public PageResponse<AdminListDto> getAdminList(UserInfo userInfo, PageRequest params){
        AdminSearch search = adminDtoMapper.toAdminSearch(params, userInfo);  // 검색_DTO
        Integer totCnt = adminMapper.getAdminCount(search);         // 건수_조회
        List<AdminVo> list = adminMapper.getAdminList(search);      // 목록_조회
        return ResponseUtil.getPageResponse(PageDto.of(params, totCnt), adminDtoMapper.toAdminDtoList(list));
    }

    @Transactional
    public void createAdmin(UserInfo userInfo, AdminCreateRequest params){
        // insert users
        String userGrade = params.getPartnerTypeCd() + "_" + params.getRoleCd();
        User user = adminDtoMapper.toUser(params, userGrade, UserStatusType.ACTIVE.getCode());
        userMapper.insertUser(user);

        // insert partner
        Partner partner = Partner.builder().parentPartnerNo(userInfo.getPartnerNo())
                                        .tenantNo(userInfo.getTenantNo())
                                        .partnerTypeCd(params.getPartnerTypeCd())
                                        .partnerCode(CodeUtil.partnerCode())
                                        .partnerName(userInfo.getUserName())
                                        .createdBy(userInfo.getUserNo())
                                        .updatedBy(userInfo.getUserNo()).build();
        partnerMapper.insertPartner(partner);

        // insert user_partner
        userMapper.insertUserPartner(UserPartner.builder().userNo(user.getUserNo()).partnerNo(partner.getPartnerNo()).build());
    }

}
