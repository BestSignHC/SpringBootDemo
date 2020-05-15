package com.hecheng.springbootquartz.runner;

import com.alibaba.fastjson.JSONArray;
import com.hecheng.springbootquartz.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TaskLoader implements CommandLineRunner {

    @Autowired
    private QuartzService quartzService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("******************");
        System.out.println("start load task");

        JSONArray objects = quartzService.queryAllJob();
        System.out.println(objects);

        System.out.println("******************");
    }
}
