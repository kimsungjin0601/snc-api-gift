package com.snc.gift.service;

import com.snc.gift.domain.User;
import com.snc.gift.dto.mapper.AdminDtoMapper;
import com.snc.gift.dto.request.AdminCreateRequest;
import com.snc.gift.mapper.UserMapper;
import com.snc.gift.type.UserStatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SystemService {
    private final PasswordEncoder passwordEncoder;

    private final AdminDtoMapper adminDtoMapper;
    private final UserMapper userMapper;

    @Transactional
    public void createSystemAdmin(AdminCreateRequest params){
        String userGrade = params.getPartnerTypeCd() + "_" + params.getRoleCd();
        params.setPassword(passwordEncoder.encode(params.getPassword()));
        User user = adminDtoMapper.toUser(params, userGrade, UserStatusType.ACTIVE.getCode());
        userMapper.insertUser(user);
    }
}
