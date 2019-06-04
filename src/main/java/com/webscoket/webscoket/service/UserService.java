package com.webscoket.webscoket.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.webscoket.webscoket.bean.dto.UserDto;
import com.webscoket.webscoket.dao.UserBindDao;
import com.webscoket.webscoket.dao.UserDao;
import com.webscoket.webscoket.model.User;
import com.webscoket.webscoket.model.UserBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService  {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserBindDao userBindDao;


    public User getUser(User user) {
        if (user.getPhone()==null){
            return null;
        }
        User bean=null;
        try {
            bean = user.selectOne(new EntityWrapper<User>().eq("phone", user.getPhone()));
        } catch (Exception e) {

        }
        return bean;
    }


    public void addUser(User user) {
        userDao.insert(user);
    }

    /**
     * 关联好友关系
     * @param user
     * @param myid
     * @return
     */
    public Boolean contactUser(UserDto user, Integer myid) {
        if (user == null) {
            return false;
        }
        User bean = userDao.selectOne(user);
        if (bean == null) {
            return false;
        }
        UserBind bind = new UserBind();
        bind.setPid(user.getId());
        bind.setUid(myid);
        bind.setCreateDate(new Date());
        Integer flag;
        try {
            flag = userBindDao.insert(bind);
            return flag == 0 ? false : true;

        } catch (Exception e) {
            return false;
        }
    }

}
