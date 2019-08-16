package com.hecheng.Sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg = "this is msg: " + System.currentTimeMillis();
        System.out.println("send msg [" + msg + "]");
        this.rabbitTemplate.convertAndSend("fanoutExchange", "", msg);
    }
}
