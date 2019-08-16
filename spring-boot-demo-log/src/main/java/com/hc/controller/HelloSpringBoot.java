package com.hc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringBoot {

    @RequestMapping("/")
    public String index() {
        return "hello spring boot!";
    }
}
