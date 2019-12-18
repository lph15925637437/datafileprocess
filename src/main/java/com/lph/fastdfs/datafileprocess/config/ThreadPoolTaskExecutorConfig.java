package com.lph.fastdfs.datafileprocess.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 扩展
 *
 * @version V1.0
 * @author: lph
 * @date: 2019/12/18 16:53
 */
@Configuration
@EnableAsync // 开启异步处理
public class ThreadPoolTaskExecutorConfig {
    /** THREADCOUNT 系统CPU核心数*/
    public static final int THREADCOUNT = Runtime.getRuntime().availableProcessors();

    public static final int QUEUESIZE = 1000;

    @Value("${thread.pool.core.pool.size}")
    private String coreSize;
    @Value("${thread.pool.max.pool.size}")
    private String maxSize;
    @Value("${thread.pool.queue.size}")
    private String queueSize;

    @Bean(value = "extendExecutor")
    public ThreadPoolTaskExecutor extendExecutor() {
        ThreadPoolTaskExecutor executor = new ExtendThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(coreSize.equals("") ? THREADCOUNT : Integer.valueOf(coreSize));
        //配置最大线程数
        executor.setMaxPoolSize(maxSize.equals("") ? THREADCOUNT + 1 : Integer.valueOf(maxSize));
        //配置队列大小
        executor.setQueueCapacity(queueSize.equals("") ? QUEUESIZE : Integer.valueOf(queueSize));
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
