package com.webscoket.webscoket.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webscoket.webscoket.dao.MessageDao;
import com.webscoket.webscoket.bean.dto.MessageDto;
import com.webscoket.webscoket.model.Message;
import com.webscoket.webscoket.service.MessAgeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class MessAgeServiceImpl extends ServiceImpl<MessageDao,Message >  implements MessAgeService  {
    @Autowired
    private MessageDao messageDao;

    /**
     * 发送消息给对方
     * @param messageDto
     * @return
     */
    public Boolean sendMessage(MessageDto messageDto) {
        Message message=new Message();
        int flag = 0;
        try {
            BeanUtils.copyProperties(messageDto,message);
            flag = messageDao.insert(message);
            MyWebSocket.sendForUser(JSON.toJSONString(messageDto), messageDto.getAccid());

        } catch (BeansException e) {
            throw new RuntimeException("消息发送错误");
        } catch (IOException e) {
            throw new RuntimeException("IO错误");
        }
        return flag==0?false:true;
    }

}
