package com.webscoket.webscoket.controller;


import com.webscoket.webscoket.bean.dto.UserDto;
import com.webscoket.webscoket.service.UserService;
import com.webscoket.webscoket.dao.UserBindDao;
import com.webscoket.webscoket.model.User;
import com.webscoket.webscoket.utils.JwtTokenUtil;
import com.webscoket.webscoket.utils.WeikeResponse;
import com.webscoket.webscoket.utils.WeikeResponseUtil;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/login")
    public WeikeResponse login(UserDto userDto) {
        User user=new User();
        BeanUtils.copyProperties(userDto,user);
        User vaildBean = userService.getUser(user);
        if (vaildBean == null) {
            return WeikeResponseUtil.fail("008","无此用户");
        }
        String password = vaildBean.getPassword();
        //TODO MD5加密加盐
        boolean equals = password.equals(userDto.getPassword());
        if (!equals){
            return WeikeResponseUtil.fail("009","密码不正确");
        }
        String token = JwtTokenUtil.generateToken(String.valueOf(userDto.getId()));
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


    @GetMapping("/register")
    @ResponseBody
    public WeikeResponse friendList(User user) {
        userService.addUser(user);
        return WeikeResponseUtil.success();
    }

    @PostMapping("/contact")
    @ResponseBody
    public WeikeResponse contact(UserDto userDto,String token) {
        String myId = JwtTokenUtil.getUsernameFromToken(token);
        if (myId==null){

        }
        Boolean flag = userService.contactUser(userDto, Integer.valueOf(myId));
        if (flag){
            return WeikeResponseUtil.success();
        }
        return WeikeResponseUtil.fail("10021","好友关联错误");
    }



}
