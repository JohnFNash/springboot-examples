package com.johnfnash.learn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.johnfnash.learn.domain.QuartzJobBean;
import com.johnfnash.learn.service.QuartzJobService;
import com.johnfnash.learn.service.TaskService;

@Component
public class MyRunner implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired     
	private TaskService taskService;
	
	@Autowired
	private QuartzJobService jobService;
	
	@Override
	public void run(String... args) throws Exception {
		// 可执行的任务列表        
		List<QuartzJobBean> taskList = jobService.findByJobStatus(QuartzJobBean.STATUS_RUNNING);     
		logger.info("初始化加载定时任务......");     
		for (QuartzJobBean job : taskList) {     
			try {
				taskService.addJob(job);     
			} catch (Exception e) {
				logger.error("add job error: " + job.getJobName() + " " + job.getJobGroup(), e);
			}
		}  
	}

}
