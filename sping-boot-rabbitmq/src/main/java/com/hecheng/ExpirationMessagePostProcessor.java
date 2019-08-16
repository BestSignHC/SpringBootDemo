package com.hecheng;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * Created by ChengH on 2018/4/11.
 */
public class ExpirationMessagePostProcessor implements MessagePostProcessor {
    private final Integer ttl; // 毫秒

    public ExpirationMessagePostProcessor(Integer ttl) {
        this.ttl = ttl;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties()
                .setExpiration(ttl.toString()); // 设置per-message的失效时间
        return message;
    }
}
