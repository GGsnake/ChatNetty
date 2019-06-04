package com.webscoket.webscoket.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.webscoket.webscoket.bean.dto.UserDto;
import com.webscoket.webscoket.service.UserService;
import com.webscoket.webscoket.dao.UserBindDao;
import com.webscoket.webscoket.model.User;
import com.webscoket.webscoket.utils.*;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param json
     * @return
     */
    @PostMapping("/login")
    public Response login(@RequestBody String json) {
        UserDto userDto = JSON.parseObject(json,UserDto.class);
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User vaildBean = userService.getUser(user);
        if (vaildBean == null) {
            return ResponseUtil.fail(ResponseCode.COMMON_USER_NOEXIST);
        }
        boolean equals = vaildBean.getPassword().equals(ToolUtil.enpPassword(userDto.getPassword()));
        if (!equals) {
            return ResponseUtil.fail(ResponseCode.COMMON_PASSWORD_ERROR);
        }
        String token = JwtTokenUtil.generateToken(String.valueOf(vaildBean.getId()));
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("token",token);
        map.put("user",vaildBean);
        return ResponseUtil.success(map);
    }

    /**
     * 注册用户
     *
     * @param userDto
     * @return
     */
    @PostMapping("/register")
    public Response register(UserDto userDto) {
        if (userDto.ifPwdNull() || userDto.ifPhoneNull()) {
            return ResponseUtil.fail(ResponseCode.COMMON_PARAMS_MISSING);
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User vaildBean = userService.getUser(user);
        if (vaildBean != null) {
            return ResponseUtil.fail(ResponseCode.COMMON_USER_EXIST);
        }
        String password = userDto.getPassword();
        // MD5加密加盐
        String passwordEncode = ToolUtil.enpPassword(password);
        user.setPassword(passwordEncode);
        userService.addUser(user);
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
