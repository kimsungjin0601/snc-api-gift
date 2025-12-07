package com.snc.gift.security;

import com.cstify.common.service.SecurityUserDetailsService;
import com.cstify.common.vo.SecurityUserDetails;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.service.UserCacheService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements SecurityUserDetailsService {
    private final UserCacheService userCacheService;

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userCacheService.getUser(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("NOT_FOUND_USER");
        }
        return new SecurityUserDetails(userInfo);
    }

    @Override
    public UserDetails loadUserByUserNo(Long userNo) throws UsernameNotFoundException {
        UserInfo userInfo = userCacheService.getUser(userNo);
        if (userInfo == null) {
            throw new UsernameNotFoundException("NOT_FOUND_USER");
        }
        return new SecurityUserDetails(userInfo);
    }
}
