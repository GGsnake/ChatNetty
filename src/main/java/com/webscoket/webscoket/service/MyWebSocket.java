package com.webscoket.webscoket.service;

import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint
@Component
@Log
public class MyWebSocket {
    @Autowired
    private RabbitTemplate template;
    private static Map<String, Session> livingSessions = new ConcurrentHashMap<>();
    @OnOpen
    public void onOpen(Session session, HttpHeaders headers) {
        livingSessions.put(String.valueOf(session.id()), session);
        session.sendText("Hello" + session.id());
        sendMessageToAll("Hello" + session.id() + "大家欢迎他");
    }

    /**
     * 接受到来自用户的输入
     * @param session
     * @param message
     */
    @OnMessage
    public void OnMessage(Session session, String message) {
        sendMessage(session, message);
    }

    /**
     * 发送消息到队列
     *
     * @param session
     * @param message
     */
    public void sendMessage(Session session, String message) {
        send(message);
    }
    /**
     * 监听队列里的新消息
     * @param data
     */
    @RabbitListener(queues = "queueName")
    public void process(String data) {
        sendMessageToAll("用户ID=+" + data);
    }

    /**
     * 群发消息给每个用户
     *
     * @param message
     */
    public void sendMessageToAll(String message) {
        livingSessions.forEach((sessionId, session) -> {
            session.sendText(message);
        });
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        sendMessageToAll("Hello" + session.id() + "退出了聊天室");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }


    public void send(String message) {
        template.convertAndSend("chat", "routingKey", message);
    }


}
