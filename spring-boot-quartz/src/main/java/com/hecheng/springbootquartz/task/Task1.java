package com.hecheng.springbootquartz.task;

import com.hecheng.springbootquartz.utils.DataUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class Task1 extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.printf("%s: %s，name: %s%n", DataUtils.getDateTime(), this.getClass().getSimpleName(), jobExecutionContext.getJobDetail().getKey().getName());
    }
}