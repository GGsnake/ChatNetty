package com.webscoket.webscoket.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.webscoket.webscoket.bean.dto.MessageDto;
import com.webscoket.webscoket.model.Message;
import com.webscoket.webscoket.service.impl.MessAgeService;
import com.webscoket.webscoket.service.impl.MyWebSocket;
import com.webscoket.webscoket.utils.*;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Log
@CrossOrigin("*")
@RequestMapping("/Mes")
@Controller
public class MessageController {

    @Autowired
    private MessAgeService messAgeService;

//    /**
//     * 发送消息
//     * @param uid
//     * @param message
//     * @return
//     */
//    @GetMapping("/mess")
//    public String getMessage(Integer uid, String message) {
//        try {
//            MyWebSocket.sendInfo(message, String.valueOf(uid));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 查询历史聊天记录
     * @return
     */
    @GetMapping("/history")
    @ResponseBody
    public Response history(String token, Integer uid, String sendtime) {
        Integer myId = null;
        Wrapper<Message> eq = null;
        try {
            myId = Integer.valueOf(JwtTokenUtil.getUsernameFromToken(token));
            Integer[] arr = {uid, myId};
            eq = new EntityWrapper<>();
            //判断有无时间线 要求 开启增量查询
            if (!"null".equals(sendtime)) {
                eq.gt("id", sendtime);
            }
            eq.in("sid", arr).in("accid", arr).orderBy("id", true);
            //暂时未分页
            List<Message> messagePage = messAgeService.selectList(eq);
            return ResponseUtil.success(messagePage);
        } catch (NumberFormatException e) {
            return ResponseUtil.fail("查询错误",e.getMessage());

        }

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
        Integer myId = Integer.valueOf(JwtTokenUtil.getUsernameFromToken(token));
        messageDto.setSid(myId);
        Message message = new Message();
        try {
            BeanUtils.copyProperties(messageDto, message);
            messAgeService.insert(messageDto);
            //发送给对方
            MyWebSocket.sendForUser(JSON.toJSONString(messageDto), messageDto.getAccid());
            return ResponseUtil.success();
        } catch (IOException e) {
            return ResponseUtil.fail(ResponseCode.WEBSOCKET_CONNECT_ERROR);
        } catch (BeansException e) {
            return ResponseUtil.fail(ResponseCode.COMMON_SYSTEM_ERROR);
        } catch (Exception e) {
            return ResponseUtil.fail(ResponseCode.SEND_MESSAGE_FAIL);
        }
    }


}
