package cn.bestsign.rocketmq.broadcasting;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class ConsumerB {
    public static void main(String[] args) throws MQClientException {
        // 指定group的name, 需要和生产者保持一致才能消费
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group_broadcasting");

        // names addr 这个不能错
        consumer.setNamesrvAddr("192.168.4.201:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        // 指定订阅的topic
        consumer.subscribe("TopicTest_Broadcasting", "");

        // 指定广播消费
        consumer.setMessageModel(MessageModel.BROADCASTING);

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
        System.out.println("ConsumerB start success.");
    }
}
