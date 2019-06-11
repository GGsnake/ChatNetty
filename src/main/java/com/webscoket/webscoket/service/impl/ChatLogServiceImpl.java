package com.webscoket.webscoket.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webscoket.webscoket.dao.ChatLogMapper;
import com.webscoket.webscoket.entity.ChatLog;
import com.webscoket.webscoket.service.ChatLogService;
import org.springframework.stereotype.Service;

@Service
public class ChatLogServiceImpl extends ServiceImpl<ChatLogMapper, ChatLog> implements ChatLogService {
}
