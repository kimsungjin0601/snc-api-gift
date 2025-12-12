package com.snc.gift.security;

import com.cstify.common.service.SecurityUserDetailsService;
import com.cstify.common.vo.SecurityUserDetails;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.service.UserCacheService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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

        List<String> roles = userCacheService.getUserAuthorities(userNo);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roles)) {
            roles.forEach((auth) -> authorities.add(new SimpleGrantedAuthority("ROLE_" + auth)));
        }
        userInfo.setAuthorities(authorities);

        return new SecurityUserDetails(userInfo);
    }
}
