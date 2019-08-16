package com.hecheng.config;

import com.hecheng.receiver.ProcessReceiver;
import com.hecheng.receiver.RejectReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ChengH on 2018/4/11.
 */

@Configuration
public class RabbitMqConfig {

    /**
     * 发送到该队列的message会在一段时间后过期进入到delay_process_queue
     * 每个message可以控制自己的失效时间
     */
    public final static String DELAY_QUEUE_PER_MESSAGE_TTL_NAME = "delay_queue_per_message_ttl";

    /**
     * 发送到该队列的message会在一段时间后过期进入到delay_process_queue
     * 队列里所有的message都有统一的失效时间
     */
    public final static String DELAY_QUEUE_PER_QUEUE_TTL_NAME = "delay_queue_per_queue_ttl";

    public final static int QUEUE_EXPIRATION = 10000;

    /**
     * 发送到该队列的message会被consumer拒绝，且不会被requeue，因为requeue被设置为false。被拒绝后会进入delay_process_queue
     */
    public final static String DELAY_QUEUE_REJECT_NAME = "delay_queue_reject";

    /**
     * 发送到该队列的message个数超过队列的最大长度之后，新的message会进入到delay_process_queue
     */
    public final static String DELAY_QUEUE_MAX_LENGTH_NAME = "delay_queue_max_length";

    /**
     * message失效后进入的队列，也就是实际的消费队列
     */
    public final static String DELAY_PROCESS_QUEUE_NAME = "delay_process_queue";

    /**
     * DLX
     */
    public final static String DELAY_EXCHANGE_NAME = "delay_exchange";

    public final static String NORMAL_QUEUE = "hello";



    public Queue helloQueue() {
            return new Queue("hello");
        }

    /**
     * 创建DLX
     *
     * @return
     */
    @Bean
    DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    /**
     * 创建delay_queue_per_message_ttl队列
     *
     * @return
     */
    @Bean
    Queue delayQueuePerMessageTTL() {
        return QueueBuilder.durable(DELAY_QUEUE_PER_MESSAGE_TTL_NAME)
                .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // DLX，dead letter发送到的exchange
                .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
                .build();
    }

    /**
     * 创建delay_queue_per_queue_ttl队列
     *
     * @return
     */
    @Bean
    Queue delayQueuePerQueueTTL() {
        return QueueBuilder.durable(DELAY_QUEUE_PER_QUEUE_TTL_NAME)
                .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // DLX
                .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
                .withArgument("x-message-ttl", QUEUE_EXPIRATION) // 设置队列的过期时间
                .build();
    }

    /**
     * 创建delay_queue_reject队列
     *
     * @return
     */
    @Bean
    Queue delayQueueReject() {
        return QueueBuilder.durable(DELAY_QUEUE_REJECT_NAME)
                .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // DLX
                .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
                .build();
    }

    /**
     * 创建delay_queue_max_length队列
     *
     * @return
     */
    @Bean
    Queue delayQueueMaxLength() {
        return QueueBuilder.durable(DELAY_QUEUE_MAX_LENGTH_NAME)
                .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // DLX
                .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
                .withArgument("x-max-length", 3)
                .build();
    }

    /**
     * 创建delay_process_queue队列，也就是实际消费队列
     *
     * @return
     */
    @Bean
    Queue delayProcessQueue() {
        return QueueBuilder.durable(DELAY_PROCESS_QUEUE_NAME)
                .build();
    }

    /**
     * 将DLX绑定到实际消费队列
     *
     * @param delayProcessQueue
     * @param delayExchange
     * @return
     */
    @Bean
    Binding binding(Queue delayProcessQueue, DirectExchange delayExchange) {
        return BindingBuilder.bind(delayProcessQueue)
                .to(delayExchange)
                .with(DELAY_PROCESS_QUEUE_NAME);
    }

    /**
     * 定义delay_process_queue队列的Listener Container
     *
     * @param connectionFactory
     * @param processListenerAdapter
     * @return
     */
    @Bean
    SimpleMessageListenerContainer processContainer(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter processListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(DELAY_PROCESS_QUEUE_NAME); // 监听delay_process_queue
        container.setMessageListener(processListenerAdapter);
        return container;
    }

    /**
     * 定义delay_queue_reject队列的Listener Container
     *
     * @param connectionFactory
     * @param rejectListenerAdapter
     * @return
     */
    @Bean
    SimpleMessageListenerContainer rejectContainer(ConnectionFactory connectionFactory, MessageListenerAdapter rejectListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(DELAY_QUEUE_REJECT_NAME); // 监听delay_queue_reject
        container.setMessageListener(rejectListenerAdapter);
        container.setDefaultRequeueRejected(false); // 关键!
        return container;
    }

    @Bean
    MessageListenerAdapter processListenerAdapter(ProcessReceiver processReceiver) {
        return new MessageListenerAdapter(processReceiver, "processMessage");
    }

    @Bean
    MessageListenerAdapter rejectListenerAdapter(RejectReceiver rejectReceiver) {
        return new MessageListenerAdapter(rejectReceiver, "rejectMessage");
    }
}
