package com.johnfnash.learn.jobs;

import java.util.List;

import org.quartz.SchedulerException;

import com.johnfnash.learn.domain.QuartzJobBean;
import com.johnfnash.learn.service.TaskService;
import com.johnfnash.learn.util.SpringUtils;

public class JobViewTest {
	
	private TaskService taskService;
	
	public JobViewTest() {
		taskService = SpringUtils.getBean("taskService");
	}
	
	public void run() {
		List<QuartzJobBean> jobs;
		try {
			System.out.print("All jobs: ");
			jobs = taskService.getAllJobs();
			for (QuartzJobBean job : jobs) {
				System.out.print(job.getJobGroup() + "_" + job.getJobName() + " " + job.getJobStatus() + "\t");
			}
			System.out.println();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
}
