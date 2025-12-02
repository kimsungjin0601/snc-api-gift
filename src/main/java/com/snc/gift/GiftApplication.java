package com.snc.gift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.snc.gift"})
public class GiftApplication {

    public static void main(String[] args) {
        SpringApplication.run(GiftApplication.class, args);
    }

}
