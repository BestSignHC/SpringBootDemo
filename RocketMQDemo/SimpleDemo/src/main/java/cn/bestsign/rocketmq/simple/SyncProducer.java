package cn.bestsign.rocketmq.simple;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class SyncProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer sysGroup = new DefaultMQProducer("simple_group");
        sysGroup.setNamesrvAddr("192.168.4.201:9876");
        sysGroup.start();

        for (int i = 0; i < 10; i++) {
            String realMsg = String.format("Hello RocketMQ_for TopicTest2, %d", i);
            Message msg = new Message("TopicTest2", "TagA", realMsg.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = sysGroup.send(msg);
            System.out.println(sendResult);
        }
        sysGroup.shutdown();
    }
}
