package com.hecheng.springbootdemoevent.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

public class HelloEvent extends ApplicationEvent {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HelloEvent(Object source, String name) {
        super(source);
        this.name = name;
    }
}
