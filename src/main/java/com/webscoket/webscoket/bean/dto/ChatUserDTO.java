package com.webscoket.webscoket.bean.dto;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mybatis Generator 2019/06/06
 */
@Data
public class ChatUserDTO  {
    private Integer id;

    /**
     * 用户手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String userPhone;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户年龄
     */
    private Byte userAge;

    /**
     * 登录密码
     */
    @NotBlank(message = "登录密码不能为空")
    private String userPassword;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 最后登录时间
     */
    private Date lastDate;

    /**
     * 最后登录时间
     */
    private String wxopenid;

    /**
     * 创建用户日期
     */
    private Date createDate;


}