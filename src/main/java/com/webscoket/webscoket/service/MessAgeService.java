package com.webscoket.webscoket.service;

import com.baomidou.mybatisplus.service.IService;
import com.webscoket.webscoket.bean.dto.MessageDto;
import com.webscoket.webscoket.model.Message;

public interface MessAgeService extends IService<Message> {
    /**
     * 发送消息给对方
     * @param messageDto
     * @return
     */
    Boolean sendMessage(MessageDto messageDto);
}
