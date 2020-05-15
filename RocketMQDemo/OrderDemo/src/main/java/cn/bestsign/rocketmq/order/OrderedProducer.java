package cn.bestsign.rocketmq.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class OrderedProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("order_group");
        producer.setNamesrvAddr("192.168.4.201:9876");
        producer.start();

        for (int i = 0; i < 20; i ++) {
            Message msg = new Message("TopicTest_Order", "", "",
                    String.format("%d: Hello Rocket MQ, Order - %d", System.currentTimeMillis(), i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendRes = producer.send(msg);
            System.out.println(sendRes);
        }
        producer.shutdown();
    }
}
