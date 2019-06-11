package com.webscoket.webscoket.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webscoket.webscoket.dao.MessageDao;
import com.webscoket.webscoket.bean.dto.MessageDto;
import com.webscoket.webscoket.model.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessAgeService extends ServiceImpl<MessageDao,Message> {
    @Autowired
    private MessageDao sqlManager;


    /**
     * 发送消息给对方
     * @param messageDto
     * @return
     */
    public Boolean allMessage(MessageDto messageDto) {
        Message message=new Message();
        int flag = 0;
        try {
            BeanUtils.copyProperties(messageDto,message);
            flag = sqlManager.insert(message);
        } catch (BeansException e) {
            throw new RuntimeException("ss");
        }
        return flag==0?false:true;
    }

}
