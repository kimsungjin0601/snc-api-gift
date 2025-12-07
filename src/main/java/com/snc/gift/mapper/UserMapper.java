package com.snc.gift.mapper;

import com.cstify.common.vo.UserInfo;
import com.snc.gift.domain.User;
import com.snc.gift.domain.UserPartner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserInfo getUserInfo(@Param("loginId") String loginId);

    UserInfo getUserInfoByUserNo(@Param("userNo") Long userNo);

    Boolean checkLoginId(@Param("loginId") String loginId);

    void insertUser(User user);

    void updateUser(User user);

    void insertUserPartner(UserPartner userPartner);
}
