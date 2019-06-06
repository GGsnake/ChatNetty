package com.webscoket.webscoket.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import com.webscoket.webscoket.bean.ChatUser;
import com.webscoket.webscoket.bean.dto.ChatUserDTO;
import com.webscoket.webscoket.bean.dto.UserDto;
import com.webscoket.webscoket.service.UserService;
import com.webscoket.webscoket.service.impl.UserServiceImpl;
import com.webscoket.webscoket.dao.UserBindDao;
import com.webscoket.webscoket.model.User;
import com.webscoket.webscoket.utils.*;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
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

    /**
     * 登录
     *
     * @param chatUserDTO
     * @return
     */
    @PostMapping("/login")
    public Response login(@Valid @RequestBody ChatUserDTO chatUserDTO) {

        try {
            ChatUser chatUser = userService.userLogin(chatUserDTO);
            String token = JwtTokenUtil.generateToken(String.valueOf(chatUserDTO.getId()));
            HashMap<String, Object> data = Maps.newHashMap();
            data.put("token", token);
            data.put("user", chatUser);
            return ResponseUtil.success(data);
        } catch (Exception e) {
            return ResponseUtil.fail("错误信息", e.getMessage());
        }

    }

    /**
     * 注册用户
     *
     * @param chatUserDTO
     * @return
     */
    @PostMapping("/register")
    public Response register(ChatUserDTO chatUserDTO) {

        return ResponseUtil.success();
    }


    @GetMapping("/list")
    @ResponseBody
    public Response friendList(String token) {
        String authToken = JwtTokenUtil.getUsernameFromToken(token);
        if (authToken == null) {

        }
        List<User> users = userBindDao.selectUserByName(authToken);
        return ResponseUtil.success(users);
    }

    @PostMapping("/contact")
    @ResponseBody
    public Response contact(UserDto userDto, String token) {
        String myId = JwtTokenUtil.getUsernameFromToken(token);
        if (myId == null) {

        }
        Boolean flag = userService.contactUser(userDto, Integer.valueOf(myId));
        if (flag) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail("10021", "好友关联错误");
    }


}
