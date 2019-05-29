package com.webscoket.webscoket.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@TableName("userbind")
public class UserBind {
    private Integer id;
    private Integer uid;
    private Integer pid;
    private Integer status;
    @TableField("create_date")
    private Date createDate;

}
