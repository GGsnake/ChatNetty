package com.webscoket.webscoket.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ChatLog extends Model<ChatLog> {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 内容
     */
    @TableField("chat_content")
    private String chatContent;

    /**
     * 标题
     */
    @TableField("chat_title")
    private String chatTitle;

    /**
     * 创建日期
     */
    @TableField("create_date")
    private Date createDate;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
