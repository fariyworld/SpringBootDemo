package com.mace.schedule.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * description: springboot自带的轻量级quartz定时任务 scheduletask
 * 单线程执行定时任务（串行任务）
 * SpringBoot定时任务默认单线程，多线程需要自行实现或配置文件
 * <br />
 * Created by mace on 16:07 2018/6/3.
 */
@Component
@Slf4j
public class HelloTask {

    //每天早上9-10点，间隔一分钟执行任务
    @Scheduled(cron = "0 0/1 * * 6 ?")
    @Async
    public void helloScheduleTask(){

        Thread current = Thread.currentThread();
        log.info("HelloTask.helloScheduleTask 定时任务1... 线程id:{} , 线程名:{}",current.getId(),current.getName());
        try {
            Thread.sleep(2000);
            log.info("睡眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0/1 * * 6 ?")
    @Async
    public void helloScheduleTask2(){

        Thread current = Thread.currentThread();
        log.info("HelloTask.helloScheduleTask2 定时任务2... 线程id:{} , 线程名:{}",current.getId(),current.getName());
    }
}
