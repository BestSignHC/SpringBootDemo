package com.hecheng;

import com.alibaba.fastjson.JSON;
import com.hecheng.domain.IntAlias;
import com.hecheng.domain.User;
import com.hecheng.mapper.cluster.IntAliasMapper;
import com.hecheng.mapper.master.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private IntAliasMapper intAliasMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testMulityDataSource() {
		int userId = 13;
		User user = userMapper.findById(userId);
		System.out.println(JSON.toJSONString(user));

		System.out.println("**********");

		long intAliasId = 151270205001000001L;
		IntAlias alias = intAliasMapper.findById(intAliasId);
		System.out.println(JSON.toJSONString(alias));
	}

}
