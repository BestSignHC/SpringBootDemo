package cn.bestsign.rocketmq.simple;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws MQClientException {
        // 指定group的name, 需要和生产者保持一致才能消费
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("simple_group");

        // names addr 这个不能错
        consumer.setNamesrvAddr("192.168.4.201:9876");

        // 指定订阅的topic
        consumer.subscribe("TopicTest2", "");

        // 注册阻塞式消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%d  : %s%n",
                        System.currentTimeMillis(), new String(msgs.get(0).getBody()));
                // 返回消费成功，类似于ACK
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("Consumer start success.");
    }
}
