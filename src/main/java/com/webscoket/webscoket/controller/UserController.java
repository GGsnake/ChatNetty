package com.webscoket.webscoket.controller;


import com.webscoket.webscoket.UserService.UserService;
import com.webscoket.webscoket.config.LoginRequired;
import com.webscoket.webscoket.dao.UserBindDao;
import com.webscoket.webscoket.model.User;
import com.webscoket.webscoket.service.MyWebSocket;
import com.webscoket.webscoket.utils.JwtTokenUtil;
import com.webscoket.webscoket.utils.WeikeResponse;
import com.webscoket.webscoket.utils.WeikeResponseUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log
@CrossOrigin("*")
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserBindDao userBindDao;

    @GetMapping("/login")
    public WeikeResponse login(Integer uid) {
        User user = new User();
        user.setId(uid);
        User user1 = userService.getUser(user);
        if (user1 == null) {
//            return WeikeResponseUtil.fail("008","无用户");
        }
        String token = JwtTokenUtil.generateToken(String.valueOf(uid));
        return WeikeResponseUtil.success(token);
    }

    @GetMapping("/list")
    @ResponseBody
    public WeikeResponse friendList(String token) {
        String authToken = JwtTokenUtil.getUsernameFromToken(token);
        if (authToken==null){

        }
        List<User> users = userBindDao.selectUserByName(authToken);
        return WeikeResponseUtil.success(users);
    }



}
