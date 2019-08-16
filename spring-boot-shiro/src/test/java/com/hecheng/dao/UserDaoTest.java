package com.hecheng.dao;


import com.hecheng.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("Tom");
        user.setPassword("123");
        user.setSalt("123");

        userDao.createUser(user);
    }
}
