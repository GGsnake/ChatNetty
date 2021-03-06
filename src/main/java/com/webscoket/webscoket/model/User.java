package com.webscoket.webscoket.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Setter
@Getter
@ToString
@TableName("user")
public class User extends Model<User> {
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
    private String password;
    private String phone;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
