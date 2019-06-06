package com.webscoket.webscoket.bean.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Integer id;
    //年龄
    private Integer age;
    //
    private String name;
    //用户名称
    private String userName;
    private Date createDate;
    private Date lastTime;
    private String signature;

}
