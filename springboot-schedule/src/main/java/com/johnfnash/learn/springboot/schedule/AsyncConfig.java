package com.johnfnash.learn.springboot.schedule;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

//@EnableAsync
@Configuration
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

	// 自定义线程池
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(100);
		// 设置线程名称前缀（前缀+线程序号作为最终的线程名称）
		//executor.setThreadNamePrefix("AsyncExecT-");
		executor.setThreadFactory(new MyNamedThreadFactory("Async"));
		// 设置拒绝策略
		//executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		executor.initialize(); //如果不初始化，导致找到不到执行器
		//ExecutorService executor = Executors.newFixedThreadPool(10, new MyNamedThreadFactory("PHS"));
		return executor;
	}
	
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new MyAsyncExceptionHandler();
	}
	
	// 自定义异常处理类
	class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

		@Override
		public void handleUncaughtException(Throwable throwable, Method method, Object... params) {
			log.info("Exception message - " + throwable.getMessage());
            log.info("Method name - " + method.getName());
		}
		
	}

	// 自定义 ThreadFactory
	static class MyNamedThreadFactory implements ThreadFactory {

		private static final AtomicInteger poolNumber = new AtomicInteger(1);
		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private String namePrefix;
		
		public MyNamedThreadFactory(String name) {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
			if(StringUtils.isEmpty(name)) {
				name = "pool";
			}
			
			namePrefix = name + "-" + poolNumber.getAndIncrement() + "-thread";
		}
		
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
			if(t.isDaemon()) {
				t.setDaemon(true);
			}
			if(t.getPriority() != Thread.NORM_PRIORITY) {
				t.setPriority(Thread.NORM_PRIORITY);
			}
			return t;
		}
		
	}
	
}
