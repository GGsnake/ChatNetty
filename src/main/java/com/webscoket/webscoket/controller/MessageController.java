package com.webscoket.webscoket.controller;


import com.webscoket.webscoket.service.MyWebSocket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/Mes")
@Controller
public class MessageController {
    @GetMapping("/mess")
    public String getMessage(Integer uid,String message) {
        try {
            MyWebSocket.sendInfo(message, String.valueOf(uid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
