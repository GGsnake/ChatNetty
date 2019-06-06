package com.webscoket.webscoket.service;

import com.baomidou.mybatisplus.service.IService;
import com.webscoket.webscoket.bean.ChatUser;
import com.webscoket.webscoket.bean.dto.ChatUserDTO;

public interface UserService extends IService<ChatUser> {
    /**
     * 用户登录
     * @param chatUserDTO
     * @return
     */
    ChatUser userLogin(ChatUserDTO chatUserDTO) throws Exception;
    /**
     * 用户登录
     * @param chatUserDTO
     * @return
     */
    Boolean userRegsiter(ChatUserDTO chatUserDTO) throws Exception;
}
