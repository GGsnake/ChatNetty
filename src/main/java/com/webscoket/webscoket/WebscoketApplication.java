package com.webscoket.webscoket;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class WebscoketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebscoketApplication.class, args);
    }

}
