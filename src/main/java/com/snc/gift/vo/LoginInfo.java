package com.snc.gift.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("LoginInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
    @JsonProperty("loginId")
    private String loginId;

    @JsonProperty("password")
    private String password;
}
