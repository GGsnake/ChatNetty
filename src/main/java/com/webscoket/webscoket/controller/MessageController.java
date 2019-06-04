package com.webscoket.webscoket.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.webscoket.webscoket.bean.dto.MessageDto;
import com.webscoket.webscoket.model.Message;
import com.webscoket.webscoket.service.MessAgeService;
import com.webscoket.webscoket.service.MyWebSocket;
import com.webscoket.webscoket.utils.JwtTokenUtil;
import com.webscoket.webscoket.utils.Response;
import com.webscoket.webscoket.utils.ResponseUtil;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Log
@CrossOrigin("*")
@RequestMapping("/Mes")
@Controller
public class MessageController {

    @Autowired
    private MessAgeService messAgeService;

    @GetMapping("/mess")
    public String getMessage(Integer uid, String message) {
        try {
            MyWebSocket.sendInfo(message, String.valueOf(uid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 历史聊天记录
     *
     * @return
     */
    @GetMapping("/history")
    @ResponseBody
    public Response history(String token, Integer uid, String sendtime) {
        String myId = JwtTokenUtil.getUsernameFromToken(token);
        String[] arr = {String.valueOf(uid), myId};
        Wrapper<Message> eq = new EntityWrapper<Message>();
        if (!"null".equals(sendtime)) {
            eq.gt("id", sendtime);
        }
        eq.in("sid", arr)
                .in("accid", arr)
                .orderBy("id", true);

//        Page<Message> messagePage = messAgeService.selectPage(new Page<Message>(1, 10),in);
        List<Message> messagePage = messAgeService.selectList(eq);

        return ResponseUtil.success(messagePage);
    }


    /**
     * 发送消息给对方
     *
     * @param messageDto
     * @return
     */
    @GetMapping("/save")
    @ResponseBody
    public Response save(MessageDto messageDto, String token) {
        String myId = JwtTokenUtil.getUsernameFromToken(token);
        messageDto.setSid(Integer.valueOf(myId));
        Message message = new Message();
        BeanUtils.copyProperties(messageDto, message);
        Boolean aBoolean = messAgeService.insert(messageDto);
        try {
            MyWebSocket.sendForUser(JSON.toJSONString(messageDto), messageDto.getAccid());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseUtil.success(aBoolean);
    }

}
