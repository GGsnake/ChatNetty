package com.webscoket.webscoket.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.webscoket.webscoket.bean.dto.MessageDto;
import com.webscoket.webscoket.model.Message;
import com.webscoket.webscoket.service.MessAgeService;
import com.webscoket.webscoket.service.MyWebSocket;
import com.webscoket.webscoket.utils.JwtTokenUtil;
import com.webscoket.webscoket.utils.WeikeResponse;
import com.webscoket.webscoket.utils.WeikeResponseUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

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

    @GetMapping("/history")
    @ResponseBody
    public WeikeResponse history() {
//        String myId = JwtTokenUtil.getUsernameFromToken(token);
        String myId = "2";
        Integer uid=1;
        String[] arr={String.valueOf(uid),myId};
        Page<Message> messagePage = messAgeService.selectPage(new Page<Message>(1, 10), new EntityWrapper<Message>().where("sid", arr).and().in("accid", arr));
        return WeikeResponseUtil.success(messagePage);
    }



    /**
     * 发送消息给对方
     * @param messageDto
     * @return
     */
    @GetMapping("/save")
    @ResponseBody
    public WeikeResponse save(MessageDto messageDto,String token) {
        String myId = JwtTokenUtil.getUsernameFromToken(token);
        messageDto.setSid(Integer.valueOf(myId));
        Message message=new Message();
        message.setSendtime(new Date());
        BeanUtils.copyProperties(messageDto,message);
        Boolean aBoolean = messAgeService.insert(messageDto);
        try {
            MyWebSocket.sendForUser(messageDto.getContent(),messageDto.getAccid());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return WeikeResponseUtil.success(aBoolean);
    }

}
