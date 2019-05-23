package com.webscoket.webscoket.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;
@Setter
@Getter
@ToString
@Table(name = "user")
public class User {
    private Integer id;
    private Integer age;
    //用户角色
    private Integer roleId;
    private String name;
    //用户名称
    private String userName;
    private Date createDate;

}
