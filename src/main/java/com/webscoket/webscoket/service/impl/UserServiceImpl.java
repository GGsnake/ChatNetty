package com.webscoket.webscoket.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.webscoket.webscoket.bean.ChatUser;
import com.webscoket.webscoket.bean.dto.ChatUserDTO;
import com.webscoket.webscoket.dao.ChatUserMapper;
import com.webscoket.webscoket.dao.UserBindDao;
import com.webscoket.webscoket.model.User;
import com.webscoket.webscoket.service.UserService;
import com.webscoket.webscoket.utils.ToolUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<ChatUserMapper, ChatUser> implements UserService {

    @Autowired
    private UserBindDao userBindDao;


//
//    public void addUser(User user) {
//        userDao.insert(user);
//    }

    //    /**
//     * 关联好友关系
//     * @param user
//     * @param myid
//     * @return
//     */
//    public Boolean contactUser(UserDto user, Integer myid) {
//        if (user == null) {
//            return false;
//        }
//        User bean = userDao.selectOne(user);
//        if (bean == null) {
//            return false;
//        }
//        UserBind bind = new UserBind();
//        bind.setPid(user.getId());
//        bind.setUid(myid);
//        bind.setCreateDate(new Date());
//        Integer flag;
//        try {
//            flag = userBindDao.insert(bind);
//            return flag == 0 ? false : true;
//
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
    @Override
    public ChatUser userLogin(ChatUserDTO chatUserDTO) {
        Throwable throwable = null;
        try {
            EntityWrapper wrapper = new EntityWrapper();
            wrapper.eq("user_phone", chatUserDTO.getUserPhone());
            ChatUser chatUser = selectOne(wrapper);
            if (chatUser == null) {
                throw new Exception("用户不存在");
            }
            String userPassword = chatUserDTO.getUserPassword();
            boolean equals = chatUser.getUserPassword().equals(ToolUtil.enpPassword(userPassword));
            if (!equals) {
                throw new Exception("密码错误");
            }
            return chatUser;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Boolean userRegsiter(ChatUserDTO chatUserDTO) {
        User user = new User();
        BeanUtils.copyProperties(chatUserDTO, user);
//        User vaildBean = chatUserDTO.getUser(user);
//        if (vaildBean != null) {
//            return ResponseUtil.fail(ResponseCode.COMMON_USER_EXIST);
//        }
//        String password = userDto.getPassword();
//        // MD5加密加盐
//        String passwordEncode = ToolUtil.enpPassword(password);
//        user.setPassword(passwordEncode);
//        userService.addUser(user);
        return null;
    }
}
