package com.hecheng;

import com.hecheng.config.RabbitMqConfig;
import com.hecheng.receiver.ProcessReceiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests2 {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	//每条消息都有不一样的延迟时间
	@Test
	public void testDelayQueuePerMessageTTL() throws InterruptedException {
		ProcessReceiver.latch = new CountDownLatch(3);
		for (int i = 1; i <= 3; i++) {
			int expiration = i * 10000;		//过期时间
			rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_QUEUE_PER_MESSAGE_TTL_NAME,
					(Object) ("Message From delay_queue_per_message_ttl with expiration " + expiration), new ExpirationMessagePostProcessor(expiration));
		}
		ProcessReceiver.latch.await();
	}

	@Test
	public void testDelayQueuePerQueueTTL() throws InterruptedException {
		ProcessReceiver.latch = new CountDownLatch(3);
		for (int i = 1; i <= 3; i++) {
			rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_QUEUE_PER_QUEUE_TTL_NAME,
					("Message From delay_queue_per_queue_ttl with expiration " + RabbitMqConfig.QUEUE_EXPIRATION));
		}
		ProcessReceiver.latch.await();
	}

	@Test
	public void testDelayQueueReject() throws InterruptedException {
		ProcessReceiver.latch = new CountDownLatch(3);
		for (int i = 1; i <= 3; i++) {
			rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_QUEUE_REJECT_NAME, "Message From delay_queue_reject");
		}
		ProcessReceiver.latch.await();
	}

	@Test
	public void testDelayQueueMaxLength() throws InterruptedException {
		ProcessReceiver.latch = new CountDownLatch(3);
		for (int i = 1; i <= 6; i++) {
			rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_QUEUE_MAX_LENGTH_NAME, "Message From delay_queue_max_length");
		}
		ProcessReceiver.latch.await();
	}

}
