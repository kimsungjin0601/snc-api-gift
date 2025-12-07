package com.snc.gift.service;

import com.cstify.common.provider.TokenProvider;
import com.cstify.common.service.RedisTokenService;
import com.cstify.common.service.TokenBlockService;
import com.cstify.common.util.RequestUtil;
import com.cstify.common.vo.TokenPayload;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignOutService {
    private final TokenProvider tokenProvider;
    private final RedisTokenService redisTokenService;
    private final TokenBlockService tokenBlockService;

    public void signOut(HttpServletRequest request) {
        String accessToken = RequestUtil.getAuthorizationToken(request);
        String tokenKey = RequestUtil.extractCookie(request, "tokenKey");
        Long userNo = tokenProvider.getUserNo(accessToken);
        tokenBlockService.blockToken(accessToken, tokenKey, userNo);
    }

    public void signOut(Long userNo) {
        String tokenKey = redisTokenService.getTokenKey(userNo);
        TokenPayload tokenPayload = redisTokenService.getTokens(tokenKey);
        if(tokenPayload != null) {
            tokenBlockService.blockToken(tokenPayload.getAccessToken(), tokenKey, userNo);
        }
    }


}
