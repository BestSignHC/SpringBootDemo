package com.hecheng;


import com.alibaba.fastjson.JSON;
import com.hecheng.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

        operations.set("hc", "this is String value");

        User s = new User();
        s.setId(22);
        s.setName("hc");
        s.setAge(24);
        operations.set("hc3", JSON.toJSONString(s));

        Set<String> keyList = stringRedisTemplate.keys("hc*");
        System.out.println(JSON.toJSONString(keyList));

        String value = operations.get("hc3");
        System.out.println(value);

        User user = JSON.parseObject(value, User.class);
        System.out.println(user.getName());
    }
}

