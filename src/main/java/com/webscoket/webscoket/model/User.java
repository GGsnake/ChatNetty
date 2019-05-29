package com.webscoket.webscoket.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Setter
@Getter
@ToString
@TableName("user")
public class User {
    @TableField(strategy= FieldStrategy.IGNORED)
    private Integer id;
    private Integer age;
    //用户角色
    private Integer roleId;
    private String name;
    //用户名称
    private String userName;
    @TableField("create_date")
    private Date createDate;
    private Date lastTime;
    private String signature;

}
