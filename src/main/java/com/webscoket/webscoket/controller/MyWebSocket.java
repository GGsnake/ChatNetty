package com.webscoket.webscoket.controller;

import io.netty.handler.codec.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint
@Component
public class MyWebSocket {
    private static Map<String, Session> livingSessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers) throws IOException {
        livingSessions.put(String.valueOf(session.id()), session);
        session.sendText("Hello" + session.id());
        sendMessageToAll("Hello" + session.id()+"大家欢迎他");
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        sendMessageToAll("Hello" + session.id()+"退出了聊天室");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void OnMessage(Session session, String message) {
        sendMessageToAll("用户ID="+session.id()+":"+message);
    }

    /**
     * 单独发送消息
     *
     * @param session
     * @param message
     */
    public void sendMessage(Session session, String message) {
        session.sendText(message);
    }

    /**
     * 群发消息
     *
     * @param message
     */
    public void sendMessageToAll(String message) {
        livingSessions.forEach((sessionId, session) -> {
            sendMessage(session, message);
        });
    }
}