package com.johnfnash.learn;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync // 开启异步事件的支持( 在定时任务的类或者方法上添加@Async)
public class AsyncConfig {

	@Value("${schedule.core.pool.size}")
	private int corePoolSize;
	
	@Value("${schedule.max.pool.size}")
	private int maxPoolSize;
	
	@Value("${scheudle.queue.capacity}")
	private int queueCapacity;
	
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.initialize();
		return executor;
	}
	
}
