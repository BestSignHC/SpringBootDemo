package com.hc.controller;

import com.hc.annotation.ArroundLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringBoot {

    @RequestMapping("/")
    public String index() {
        return "hello spring boot!";
    }

    @ArroundLog("HustHC")
    @RequestMapping("/ping")
    public String pingAction() {
        return "pong!";
    }
}
