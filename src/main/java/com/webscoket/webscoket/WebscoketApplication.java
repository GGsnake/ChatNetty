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
//    public static void main(String[] args) {
//        int[] arr={23,11,3333,22,123,44,221,9};
//
//
//    }
//    public void sort(int start,int end,int[] arr){
//
//    }


}
