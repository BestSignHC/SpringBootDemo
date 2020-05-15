package com.hecheng.springbootquartz;

import com.alibaba.fastjson.JSON;
import com.hecheng.springbootquartz.entity.QuartzTask;
import com.hecheng.springbootquartz.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskManageController {

    @Autowired
    private QuartzService quartzService;

    @PostMapping("/task/add")
    public String addTaskAction(@RequestBody  QuartzTask task) {
        System.out.println("start to add task: ");
        System.out.println(JSON.toJSON(task));
        String clz = task.getClz();
        try {
            Class<?> taskClz = Class.forName(clz);
            if (!QuartzJobBean.class.isAssignableFrom(taskClz)) {
                return "not a QuartzJobBean";
            }
            quartzService.addTask((Class<? extends QuartzJobBean>) taskClz, task.getName(), task.getGroup(), task.getCorn(), JSON.parseObject(task.getData()));
            quartzService.triggerJob(task.getName(), task.getGroup());
        } catch (ClassNotFoundException e) {
            return "not found : " + task.getClz();
        }
        return "success";
    }
}
