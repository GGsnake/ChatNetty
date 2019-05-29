package com.webscoket.webscoket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableRabbit
@MapperScan("com.webscoket.webscoket.dao")
public class WebscoketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebscoketApplication.class, args);
    }

}
