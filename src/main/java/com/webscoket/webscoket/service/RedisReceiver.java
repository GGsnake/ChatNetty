package com.webscoket.webscoket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisReceiver {
    @Autowired
    RedisService redisService;

    public String receiveMessage(String message) {
        //这里是收到通道的消息之后执行的方法
        return message;
    }
}
