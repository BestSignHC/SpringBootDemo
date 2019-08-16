package com.hecheng.receiver;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ChengH on 2018/4/11.
 */

@Component
public class RejectReceiver {
    public static CountDownLatch latch;

    public void rejectMessage(String message) {
        System.out.println("Reject <" + message + ">");
        if (latch != null) {
            latch.countDown();
        }
    }
}
