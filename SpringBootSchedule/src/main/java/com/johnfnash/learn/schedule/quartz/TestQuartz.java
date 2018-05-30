package com.johnfnash.learn.schedule.quartz;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

// 任务类
public class TestQuartz extends QuartzJobBean {

	// 执行定时任务
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("quartz task " + new Date());
	}

}
