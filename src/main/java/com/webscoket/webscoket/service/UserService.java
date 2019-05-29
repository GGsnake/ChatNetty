package com.webscoket.webscoket.service;

import com.webscoket.webscoket.dao.UserDao;
import com.webscoket.webscoket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private  UserDao userDao;


    public User getUser(User user){
        User user1 = userDao.selectOne(user);
        return user1;
    }


    public void addUser(User user){
        userDao.insert(user);
    }

}
