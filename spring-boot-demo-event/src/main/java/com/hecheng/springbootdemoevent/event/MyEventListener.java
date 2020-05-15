package com.hecheng.springbootdemoevent.event;

import com.alibaba.fastjson.JSON;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener {
    @EventListener
    private void processHelloEvent(HelloEvent helloEvent) {
        System.out.println("*******");
        System.out.println(JSON.toJSONString(helloEvent));
        System.out.println("*******");
    }
}
