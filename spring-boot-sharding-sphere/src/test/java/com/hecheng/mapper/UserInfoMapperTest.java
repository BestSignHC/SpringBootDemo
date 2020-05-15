package com.hecheng.mapper;

import com.hecheng.entity.UserInfo;
import com.hecheng.utils.SnowFlakeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void addTest() {
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(1, 1);
        for (int i = 0; i < 1000; i++) {
            UserInfo u = new UserInfo();
            u.setUserId(snowFlakeUtil.nextId());
            u.setAccount("account_" + i);
            u.setPassword("pwd_" + i);
            u.setUserName("name_" + i);
            userInfoMapper.add(u);
        }
    }
}