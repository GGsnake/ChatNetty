package com.webscoket.webscoket.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.util.Date;
/**
 *  message
 * @author GGsnake 2019-05-27
 */
@Data
@TableName("message")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;

    /**
     * content
     */
    private String content;

    /**
     * 接收人id
     */
    private String accid;

    /**
     * 发送人id
     */
    private Integer sid;

    /**
     * sendtime
     */
    private Date sendtime;

    /**
     * status
     */
    private Date status;



}