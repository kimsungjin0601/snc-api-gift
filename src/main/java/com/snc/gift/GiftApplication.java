package com.snc.gift;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.cstify.common", "com.snc.gift"})
@MapperScan("com.snc.gift.mapper")
public class GiftApplication {

    static void main(String[] args) {
        SpringApplication.run(GiftApplication.class, args);
    }

}
