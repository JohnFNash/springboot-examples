package com.johnfnash.learn.schedule.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 配置类
@Configuration
public class QuartzConfig {

	// 配置定时任务
	@Bean
	public JobDetail testQuartzDetail() {
		return JobBuilder.newJob(TestQuartz.class).withIdentity("testQuartz")
						.storeDurably().build();
	}
	
	// 配置定时任务的触发器，也就是什么时候触发执行定时任务
	@Bean(name = "jobTrigger")
	public Trigger testQuartzTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(10)  // 设置时间周期单位（秒）
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(testQuartzDetail())
					.withIdentity("testQuartz")
					.withSchedule(scheduleBuilder)
					.build();
	}
}
