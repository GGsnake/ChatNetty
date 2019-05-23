package com.webscoket.webscoket.UserService;

import com.webscoket.webscoket.dao.UserDao;
import com.webscoket.webscoket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private  UserDao userDao;
    public User getUser(User user){
        User user1 = userDao.templateOne(user);
        return user1;
    }

}
