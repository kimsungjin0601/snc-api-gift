package com.snc.gift.service;

import com.cstify.common.exception.NotFoundException;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.domain.Member;
import com.snc.gift.domain.User;
import com.snc.gift.dto.mapper.MemberDtoMapper;
import com.snc.gift.dto.mapper.UserDtoMapper;
import com.snc.gift.dto.request.MemberUpdateRequest;
import com.snc.gift.dto.response.MemberInfoResponse;
import com.snc.gift.mapper.MemberMapper;
import com.snc.gift.mapper.UserMapper;
import com.snc.gift.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDtoMapper memberDtoMapper;
    private final MemberMapper memberMapper;
    private final UserDtoMapper userDtoMapper;
    private final UserMapper userMapper;

    public MemberInfoResponse getMemberInfo(UserInfo userInfo){
        MemberVo memberInfo = memberMapper.getMemberInfo(userInfo.getUserNo());
        if(memberInfo == null){
            throw new NotFoundException();
        }
        return memberDtoMapper.toMemberInfoResponse(memberInfo);
    }

    @Transactional
    public void updateMemberByMe(MemberUpdateRequest params){
        User user = userDtoMapper.toUser(params);
        userMapper.updateUser(user);

        Member member = memberDtoMapper.toMember(params);
        memberMapper.updateMemberByMe(member);
    }
}
