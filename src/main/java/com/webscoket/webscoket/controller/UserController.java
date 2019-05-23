package com.webscoket.webscoket.controller;


import com.webscoket.webscoket.UserService.UserService;
import com.webscoket.webscoket.model.User;
import com.webscoket.webscoket.service.MyWebSocket;
import com.webscoket.webscoket.utils.JwtTokenUtil;
import com.webscoket.webscoket.utils.WeikeResponse;
import com.webscoket.webscoket.utils.WeikeResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public WeikeResponse login (Integer uid) {
        User user=new User();
        user.setId(uid);
        User user1 = userService.getUser(user);
        if (user1==null){
            return WeikeResponseUtil.fail("008","无用户");
        }
        String token = JwtTokenUtil.generateToken(String.valueOf(uid));
        return WeikeResponseUtil.success(token);
    }
}
