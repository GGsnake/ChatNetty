package com.webscoket.webscoket.service;

import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Log
@ServerEndpoint("/websocket/{username}")
@Component
public class MyWebSocket {
    /**
     * 在线人数
     */
    public static int onlineNumber = 0;

    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();

    @Autowired
    private RabbitTemplate template;
    private static Map<String, Session> livingSessions = new ConcurrentHashMap<>();

    /**
     * 新用户建立连接的入口
     * @param session
     * @param headers
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
//        livingSessions.put(String.valueOf(session.id()), session);
//        session.sendText("Hello" + session.id());
//        sendMessageToAll("Hello" + session.id() + "大家欢迎他");
        onlineNumber++;
        logger.info("现在来连接的客户id："+session.getId()+"用户名："+username);
        this.username = username;
        this.session = session;
        logger.info("有新连接加入！ 当前在线人数" + onlineNumber);
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
            //先给所有人发送通知，说我上线了
            Map<String,Object> map1 = Maps.newHashMap();
            map1.put("messageType",1);
            map1.put("username",username);
            sendMessageAll(JSON.toJSONString(map1),username);

            //把自己的信息加入到map当中去
            clients.put(username, this);
            //给自己发一条消息：告诉自己现在都有谁在线
            Map<String,Object> map2 = Maps.newHashMap();
            map2.put("messageType",3);
            //移除掉自己
            Set<String> set = clients.keySet();
            map2.put("onlineUsers",set);
            sendMessageTo(JSON.toJSONString(map2),username);
        }
        catch (IOException e){
            logger.info(username+"上线的时候通知所有人发生了错误");
        }
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
