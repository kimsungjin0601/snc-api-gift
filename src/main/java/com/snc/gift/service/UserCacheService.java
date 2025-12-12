package com.snc.gift.service;

import com.cstify.common.provider.RedisProvider;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.mapper.PartnerMapper;
import com.snc.gift.mapper.UserMapper;
import com.snc.gift.vo.PartnerVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCacheService {

    private final RedisProvider redisProvider;

    private final UserMapper userMapper;

    private final PartnerMapper partnerMapper;

    private final Duration ttl = Duration.ofHours(1);

    private String getKey(Long userNo) {
        return "user:" + userNo;
    }

    public UserInfo getUser(String username) {
        UserInfo userInfo = userMapper.getUserInfo(username);
        if(userInfo != null) {
            this.getUserPartner(userInfo);
            String key = getKey(userInfo.getUserNo());
            redisProvider.set(key, userInfo, ttl);
        }
        return userInfo;
    }

    public UserInfo getUser(Long userNo) {
        String key = getKey(userNo);

        UserInfo cacheUser = (UserInfo) redisProvider.get(key);
        if(cacheUser != null) {
            return cacheUser;
        }

        UserInfo userInfo = userMapper.getUserInfoByUserNo(userNo);
        if(userInfo != null) {
            redisProvider.set(key, userInfo, ttl);
        }

        return userInfo;
    }

    public List<String> getUserAuthorities(Long userNo) {
        UserInfo userInfo = getUser(userNo);
        List<String> roles = userInfo.getRoles();
        if (CollectionUtils.isEmpty(roles)) {
            roles = new ArrayList<>();
            roles.add(userInfo.getUserGrade());
        }
        return roles;
    }

    public void evictUser(Long userNo) {
        redisProvider.delete(getKey(userNo));
    }

    public void updateUser(UserInfo userInfo) {
        String key = getKey(userInfo.getUserNo());
        redisProvider.set(key, userInfo, ttl);
    }

    private void getUserPartner(UserInfo userInfo) {
        PartnerVo partner = partnerMapper.getUserPartner(userInfo.getUserNo());
        if(partner != null) {
            BeanUtils.copyProperties(partner, userInfo);
        }
    }
}
