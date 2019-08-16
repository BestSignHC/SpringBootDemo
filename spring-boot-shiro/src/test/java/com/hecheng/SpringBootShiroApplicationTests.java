package com.hecheng;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootShiroApplicationTests {

	@Test
	public void contextLoads() {
		// 用户名
		String username = "Admin";
		// 用户密码
		String password = "123";
		// 加密方式
		String hashAlgorithName = "MD5";
		// 加密次数
		int hashIterations = 1;
		ByteSource salt = ByteSource.Util.bytes("Admin" + "admin");
		System.out.println(salt);
		Object obj = new SimpleHash(hashAlgorithName, password,
				salt, hashIterations);
		System.out.println(obj);
	}

}
