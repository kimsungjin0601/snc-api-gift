package com.snc.gift.service;

import com.cstify.common.provider.RedisProvider;
import com.cstify.common.vo.UserInfo;
import com.snc.gift.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class UserCacheService {

    private final RedisProvider redisProvider;

    private final UserMapper userMapper;

    private final Duration ttl = Duration.ofHours(1);

    private String getKey(Long userNo) {
        return "user:" + userNo;
    }

    public UserInfo getUser(String username) {
        UserInfo userInfo = userMapper.getUserInfo(username);
        if(userInfo != null) {
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

    public void evictUser(Long userNo) {
        redisProvider.delete(getKey(userNo));
    }

    public void updateUser(UserInfo userInfo) {
        String key = getKey(userInfo.getUserNo());
        redisProvider.set(key, userInfo, ttl);
    }
}
