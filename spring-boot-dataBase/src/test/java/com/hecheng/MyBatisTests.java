package com.hecheng;

import com.alibaba.fastjson.JSON;
import com.hecheng.domain.User;
import com.hecheng.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testMyBatis() {
		int id = 1;

		User user = userMapper.findById(id);
		System.out.println("find end: ");
		System.out.println(JSON.toJSONString(user));
	}

}
