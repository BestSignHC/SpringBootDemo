package com.hecheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String indexAction(Map<String, Object> model) {
        model.put("name", "Spring-boot");

        return "index";
    }
}
