package com.hecheng.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class SpingBootZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpingBootZuulApplication.class, args);
    }

}
