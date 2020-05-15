package com.hecheng.springbootquartz.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class QuartzService {
    @Autowired
    private Scheduler scheduler;

    @PostConstruct //在服务器加载Servlet, 完成构造函数后运行，并且只会被服务器执行一次。
    public void startScheduler() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增一个任务
     * @param taskClass
     * @param name
     * @param group
     * @param corn
     * @param data
     */
    public void addTask(Class<? extends QuartzJobBean> taskClass, String name, String group, String corn, Map data) {
        JobDetail detail = JobBuilder.newJob(taskClass).withIdentity(name, group)
                .build();

        if (null != data && data.size() > 0) {
            detail.getJobDataMap().putAll(data);
        }

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group)
                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(CronScheduleBuilder.cronSchedule(corn))
                .startNow()
                .build();

        try {
            scheduler.scheduleJob(detail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void deleteJob(String name, String group) {
        try {
            JobKey jobKey = new JobKey(name, group);
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void pauseJob(String name, String group) {
        try {
            JobKey jobKey = new JobKey(name, group);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void resumeJob(String name, String group) {
        try {
            JobKey jobKey = new JobKey(name, group);
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 立刻执行一个任务
     * @param name
     * @param group
     */
    public void triggerJob(String name, String group) {
        try {
            JobKey jobKey = new JobKey(name, group);
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public JSONArray queryAllJob() {
        JSONArray result = new JSONArray();

        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey key : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(key);
                for (Trigger trigger : triggers) {
                    JSONObject item = new JSONObject();
                    item.put("name", key.getName());
                    item.put("group", key.getGroup());
                    item.put("description", "触发器：" + trigger.getKey());

                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    item.put("jobStatus", triggerState.name());

                    if (trigger instanceof CronTrigger) {
                        item.put("cron", ((CronTrigger) trigger).getCronExpression());
                    }

                    result.add(item);
                }

            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return result;
    }

    public JSONArray queryRunJobs() {
        JSONArray result = new JSONArray();

        try {
            List<JobExecutionContext> currentlyExecutingJobs = scheduler.getCurrentlyExecutingJobs();
            for (JobExecutionContext context : currentlyExecutingJobs) {
                JSONObject item = new JSONObject();

                JobDetail jobDetail = context.getJobDetail();
                Trigger trigger = context.getTrigger();

                item.put("name", jobDetail.getKey().getName());
                item.put("group", jobDetail.getKey().getGroup());
                item.put("description", "触发器：" + trigger.getKey());

                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                item.put("jobStatus", triggerState.name());

                if (trigger instanceof CronTrigger) {
                    item.put("cron", ((CronTrigger) trigger).getCronExpression());
                }

                result.add(item);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
