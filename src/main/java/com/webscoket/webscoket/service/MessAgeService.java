package com.webscoket.webscoket.service;

import com.webscoket.webscoket.dao.MessageDao;
import com.webscoket.webscoket.bean.dto.MessageDto;
import com.webscoket.webscoket.model.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessAgeService {
    @Autowired
    private MessageDao sqlManager;

    /**
     * 发送消息给对方
     * @param messageDto
     * @return
     */
    public Boolean addMessage(MessageDto messageDto) {
        Message message=new Message();
        message.setSendtime(new Date());
        BeanUtils.copyProperties(messageDto,message);
        int flag = sqlManager.insert(message);
        return flag==0?false:true;
    }
    /**
     * 发送消息给对方
     * @param messageDto
     * @return
     */
    public Boolean allMessage(MessageDto messageDto) {
        Message message=new Message();
        message.setSendtime(new Date());
        BeanUtils.copyProperties(messageDto,message);
        int flag = sqlManager.insert(message);
        return flag==0?false:true;
    }

}
