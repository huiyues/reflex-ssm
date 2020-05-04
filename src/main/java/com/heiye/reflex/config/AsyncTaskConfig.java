package com.heiye.reflex.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;

import java.util.concurrent.Executor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: heiye
 * @Date: 2020/4/17
 * @Time: 21:45
 * Description: No Description
 */
@EnableScheduling
@Configuration
public class AsyncTaskConfig implements SchedulingConfigurer, AsyncConfigurer {

    /**
     * 线程池的线程数量
     */
    private int corePoolSize = 5;

    /**
     * 初始化线程池
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler taskScheduler(){

        // 初始化线程次和配置线程数量
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        scheduler.setPoolSize(corePoolSize);
        return scheduler;
    }


    /**
     * The {@link Executor} instance to be used when processing async
     * method invocations.
     */
    @Override
    public Executor getAsyncExecutor() {
        return taskScheduler();
    }

    /**
     * The {@link AsyncUncaughtExceptionHandler} instance to be used
     * when an exception is thrown during an asynchronous method execution
     * with {@code void} return type.
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

    /**
     * Callback allowing a {@link TaskScheduler
     * TaskScheduler} and specific {@link Task Task}
     * instances to be registered against the given the {@link ScheduledTaskRegistrar}
     *
     * @param taskRegistrar the registrar to be configured.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(taskScheduler());
    }
}
