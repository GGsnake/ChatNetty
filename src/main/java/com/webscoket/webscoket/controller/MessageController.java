package com.webscoket.webscoket.controller;

import com.webscoket.webscoket.dto.MessageDto;
import com.webscoket.webscoket.model.Message;
import com.webscoket.webscoket.service.MessAgeService;
import com.webscoket.webscoket.service.MyWebSocket;
import com.webscoket.webscoket.utils.JwtTokenUtil;
import com.webscoket.webscoket.utils.WeikeResponse;
import com.webscoket.webscoket.utils.WeikeResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public String history(Integer uid, String message) {
        try {
            MyWebSocket.sendInfo(message, String.valueOf(uid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 新增
     */
    @GetMapping("/save")
    @ResponseBody
    public WeikeResponse save(MessageDto messageDto,String token) {
        String myuid = JwtTokenUtil.getUsernameFromToken(token);
        messageDto.setSid(Integer.valueOf(myuid));
        Boolean aBoolean = messAgeService.addMessage(messageDto);
        try {
            MyWebSocket.sendForUser(messageDto.getContent(),messageDto.getAccid());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return WeikeResponseUtil.success(aBoolean);
    }

//    /**
//     * 删除
//     */
//    @PostMapping("/delete")
//    public Object delete(int id) {
//        Message message = sqlManager.unique(Message.class, id);
//        if (message != null) {
//            sqlManager.deleteById(id);
//            return ApiReturnUtil.success("删除成功");
//        } else {
//            return ApiReturnUtil.error("没有找到该对象");
//        }
//    }
//
//    /**
//     * 查询
//     */
//    @PostMapping("/find")
//    public Object find(int id) {
//        Message message = sqlManager.unique(Message.class, id);
//        if (message != null) {
//            return ApiReturnUtil.success(message);
//        } else {
//            return ApiReturnUtil.error("没有找到该对象");
//        }
//    }
//
//    /**
//     * 分页查询
//     */
//    @PostMapping("/list")
//    public Object list(Message message,
//                       @RequestParam(required = false, defaultValue = "0") int pageNumber,
//                       @RequestParam(required = false, defaultValue = "10") int pageSize) {
//        List<Message> list = sqlManager.query(Message.class).select();
//        return ApiReturnUtil.success(list);
//    }
}
