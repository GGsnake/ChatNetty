package com.webscoket.webscoket.bean.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChatLogDto  {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 内容
     */
    private String chatContent;

    /**
     * 标题
     */
    private String chatTitle;

    /**
     * 创建日期
     */
    private Date createDate;

}
