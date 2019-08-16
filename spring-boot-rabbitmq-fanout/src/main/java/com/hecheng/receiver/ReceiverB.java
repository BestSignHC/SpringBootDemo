package com.hecheng.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "BQueue-For-svc1")
public class ReceiverB {

    @RabbitHandler
    public void process(String msg) {
        System.out.println(this.getClass().getName() + ": " + msg);
    }
}
