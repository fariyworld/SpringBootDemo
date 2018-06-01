package com.mace.schedule.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * description: 并行任务(多线程定时任务) 配置类
 * <br />
 * Created by mace on 14:20 2018/5/22.
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig {

    @Bean
    public Executor taskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(AsyncProperties.corePoolSize);
        executor.setMaxPoolSize(AsyncProperties.maxPoolSize);
        executor.setQueueCapacity(AsyncProperties.queueCapacity);
        //设置线程名开头
        executor.setThreadNamePrefix(AsyncProperties.threadNamePrefix);
        executor.initialize();

        log.info("schedule定时任务线程池初始化成功，线程数: {}, 最大线程数: {}, 队列容量: {}, 线程名前缀:{}",
                AsyncProperties.corePoolSize,
                AsyncProperties.maxPoolSize,
                AsyncProperties.queueCapacity,
                AsyncProperties.threadNamePrefix
        );

        return executor;
    }
}
