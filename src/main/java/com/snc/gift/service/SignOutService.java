package com.snc.gift.service;

import com.cstify.common.provider.TokenProvider;
import com.cstify.common.service.RedisTokenService;
import com.cstify.common.service.TokenBlockService;
import com.cstify.common.util.RequestUtil;
import com.cstify.common.vo.TokenPayload;
import com.cstify.common.vo.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignOutService {
    private final TokenProvider tokenProvider;
    private final RedisTokenService redisTokenService;
    private final TokenBlockService tokenBlockService;

    public void signOut(UserInfo userInfo, HttpServletRequest request) {
        String tokenKey = redisTokenService.getTokenKey(userInfo.getUserNo());
        String accessToken = RequestUtil.getAuthorizationToken(request);
        Long userNo = tokenProvider.getUserNo(accessToken);
        tokenBlockService.blockRefreshToken(accessToken, tokenKey, userNo);
    }

    public void signOut(Long userNo) {
        String tokenKey = redisTokenService.getTokenKey(userNo);
        TokenPayload tokenPayload = redisTokenService.getTokens(tokenKey);
        if(tokenPayload != null) {
            tokenBlockService.blockRefreshToken(tokenPayload.getAccessToken(), tokenKey, userNo);
        }
    }


}
