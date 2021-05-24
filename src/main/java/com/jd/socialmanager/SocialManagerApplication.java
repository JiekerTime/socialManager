package com.jd.socialmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.jd.socialmanager.dao")
@SpringBootApplication()
public class SocialManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialManagerApplication.class, args);
    }

}
