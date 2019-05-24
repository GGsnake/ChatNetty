package com.webscoket.webscoket.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

@Setter
@Getter
@ToString
@Table(name = "userbind")
public class UserBind {
    private Integer id;
    private Integer uid;
    private Integer pid;
    private Integer status;
    private Date createDate;

}
