package com.snc.gift.service;

import com.cstify.common.exception.DuplicateException;
import com.cstify.common.exception.NotFoundException;
import com.snc.gift.domain.Member;
import com.snc.gift.domain.User;
import com.snc.gift.domain.UserPartner;
import com.snc.gift.dto.mapper.UserDtoMapper;
import com.snc.gift.dto.request.SignUpRequest;
import com.snc.gift.mapper.MemberMapper;
import com.snc.gift.mapper.PartnerMapper;
import com.snc.gift.mapper.UserMapper;
import com.snc.gift.type.UserStatusType;
import com.snc.gift.type.UserType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final PasswordEncoder passwordEncoder;
    private final UserDtoMapper userDtoMapper;
    private final UserMapper userMapper;
    private final MemberMapper memberMapper;
    private final PartnerMapper partnerMapper;

    public void signup(SignUpRequest params, HttpServletRequest request, HttpServletResponse response){
        // 로그인 ID 중복 체크
        boolean isDuplicate = userMapper.checkLoginId(params.getLoginId());
        if(isDuplicate){
            throw new DuplicateException("LOGIN_ID_DUPLICATE");
        }

        User user = userDtoMapper.toUser(params);
        user.setUserStatusCd(UserStatusType.ACTIVE.getCode());
        user.setUserGrade(UserType.MEMBER.getCode());
        user.setPassword(passwordEncoder.encode(params.getPassword()));
        userMapper.insertUser(user);

        // 회원 등록
        Member member = Member.builder().userNo(user.getUserNo()).build();
        memberMapper.insertMember(member);

        // 파트너 등록
        Long partnerNo = partnerMapper.getPartnerNoByDomain(request.getServerName());
        if(partnerNo == null){
            throw new NotFoundException("PARTNER_NOT_FOUND");
        }

        UserPartner userPartner = UserPartner.builder().userNo(user.getUserNo()).partnerNo(partnerNo).build();
        userMapper.insertUserPartner(userPartner);
    }
}
