package com.johnfnash.learn.springboot.schedule.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;

@Component
@Slf4j
public class TestTask {

	@Scheduled(cron = "0/5 * * * * ?")
	@SchedulerLock(name = "testJob", lockAtLeastFor = 6000)
	public void doTask() throws InterruptedException {
		log.info(Thread.currentThread().getName()+"===task run");
		Thread.sleep(6000);
		log.info(Thread.currentThread().getName()+"===task end");
	}
	
	@Async
	@SchedulerLock(name = "testJob2", lockAtLeastFor = 4000)
	@Scheduled(initialDelay = 3000, fixedDelay = 5000)
	public void doTask2() throws InterruptedException {
		log.info(Thread.currentThread().getName()+"===task2 run");
//		Thread.sleep(6000);
//		log.info(Thread.currentThread().getName()+"===task2 end");
	}
	
//	@Async
//	@Scheduled(initialDelay = 3000, fixedRate = 5000)
//	@SchedulerLock(name = "testJob3")
//	public void doTask3() throws InterruptedException {
//		log.info(Thread.currentThread().getName()+"===task3 run");
//		Thread.sleep(2000);
//		log.info(Thread.currentThread().getName()+"===task3 end");
//	}
	
}
