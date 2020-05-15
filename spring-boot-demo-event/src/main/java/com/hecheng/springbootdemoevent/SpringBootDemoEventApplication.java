package com.hecheng.springbootdemoevent;

import com.hecheng.springbootdemoevent.event.HelloEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDemoEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoEventApplication.class, args);
        ApplicationContext applicationContext = MyContext.getApplicationContext();

        applicationContext.publishEvent(new HelloEvent(new Object(), "hello event1"));
        applicationContext.publishEvent(new HelloEvent(new Object(), "hello event2"));
    }

}
