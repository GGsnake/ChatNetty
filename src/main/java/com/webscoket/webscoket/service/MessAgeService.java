package com.webscoket.webscoket.service;

import com.webscoket.webscoket.dao.MessageDao;
import com.webscoket.webscoket.dao.UserDao;
import com.webscoket.webscoket.dto.MessageDto;
import com.webscoket.webscoket.model.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessAgeService {
    @Autowired
    private MessageDao sqlManager;
    public Boolean addMessage(MessageDto messageDto) {
        Message message=new Message();
        BeanUtils.copyProperties(messageDto,message);
        message.setSendtime(new Date());
        int flag = sqlManager.insert(message);
        return flag==0?false:true;
    }

}
