package com.snc.gift.mapper;

import com.snc.gift.domain.Member;
import com.snc.gift.vo.MemberVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    MemberVo getMemberInfo(@Param("userNo") Long userNo);

    void insertMember(Member member);

    void updateMemberByMe(Member member);
}
