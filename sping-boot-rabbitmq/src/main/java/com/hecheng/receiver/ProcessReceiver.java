package com.hecheng.receiver;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ChengH on 2018/4/11.
 */

@Component
public class ProcessReceiver {
    public static CountDownLatch latch;

    //真正的处理事务的代码
    public void processMessage(String message) {
        System.out.println(System.currentTimeMillis() + ": Received <" + message + ">");
        if (latch != null) {
            latch.countDown();
        }
    }
}
