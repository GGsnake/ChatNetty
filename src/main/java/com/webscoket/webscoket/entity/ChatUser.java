package com.webscoket.webscoket.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * chat_user
 *
 * @author ljp 2019-06-22
 */
@Data
public class ChatUser extends Model<ChatUser>{
    /**
     * id
     */
    private Integer id;

    /**
     * 用户手机号
     */
    @TableField(value = "user_phone")
    private String userPhone;

    /**
     * 用户昵称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户年龄
     */
    @TableField(value = "user_age")
    private Integer userAge;

    /**
     * 登录密码
     */
    @TableField(value = "user_password")
    private String userPassword;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 最后登录时间
     */
    @TableField(value = "last_date")
    private Date lastDate;

    /**
     * 最后登录时间
     */
    private String wxOpenid;

    /**
     * 创建用户日期
     */
    @TableField(value ="create_date")
    private Date createDate;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
