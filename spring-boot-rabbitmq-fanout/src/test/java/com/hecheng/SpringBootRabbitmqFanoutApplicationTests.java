package com.hecheng;

import com.hecheng.Sender.FanoutSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitmqFanoutApplicationTests {

	@Autowired
	private FanoutSender sender;

	@Test
	public void send() {
		sender.send();
	}
}
