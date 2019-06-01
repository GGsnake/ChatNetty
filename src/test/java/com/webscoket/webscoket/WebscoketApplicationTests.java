package com.webscoket.webscoket;

import com.webscoket.webscoket.dao.UserDao;
import com.webscoket.webscoket.service.UserService;
import com.webscoket.webscoket.model.User;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebscoketApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Test
    public void contextLoads() {
        User user=new User();
        user.setName("ljp");
//        User user1 = userService.getUser(user);
//        log.warning(user1.toString());
    }
    @Test
    public void last() {
        User user=new User();
        user.setId(1);
        user.setLastTime(new Date());
        int unique = userDao.updateById(user);
//        log.warning(uniqu));
    }

}
