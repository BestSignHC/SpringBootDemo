package com.hecheng;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.Queue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootShiroApplicationTests {

	@Test
	public void contextLoads() {
		// 用户名
		String username = "Per";
		// 用户密码
		String password = "123";
		// 加密方式
		String hashAlgorithName = "MD5";
		// 加密次数
		int hashIterations = 1;
		ByteSource salt = ByteSource.Util.bytes("Per" + "per");
		System.out.println(salt);
		Object obj = new SimpleHash(hashAlgorithName, password,
				salt, hashIterations);
		System.out.println(obj);
	}

	@Test
	public void test() {
		SecretKey key = null;
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			key = keyGenerator.generateKey();
			System.out.println(Base64.getEncoder().encodeToString(key.getEncoded()));
		} catch (NoSuchAlgorithmException e) {
		}
	}

	@Test
	public void testQueue() {
		Queue q = new LinkedList();
		q.add(11);
		q.add(22);
		q.add(33);

		System.out.println(q.remove());
		System.out.println(q.size());
	}

}
