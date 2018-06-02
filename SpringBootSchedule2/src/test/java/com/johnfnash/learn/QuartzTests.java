package com.johnfnash.learn;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnfnash.learn.domain.QuartzJobBean;
import com.johnfnash.learn.service.QuartzJobService;
import com.johnfnash.learn.service.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzTests {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private QuartzJobService moduleService;
	
	@Test
	public void testAddJob() throws SchedulerException {
		QuartzJobBean job = new QuartzJobBean("task_5", "group_1", "1", "0", "*/10 * * * * ?",
					"task 5	", null, "com.johnfnash.learn.jobs.JobTest", "run");
		taskService.addJob(job);
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPauseJob() {
		QuartzJobBean job = moduleService.getOne(18L);
		try {
			Thread.sleep(11000);
			taskService.pauseJob(job);
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testResumeJob() {
		QuartzJobBean job = moduleService.getOne(18L);
		try {
			taskService.pauseJob(job);
			Thread.sleep(11000);
			taskService.resumeJob(job);
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateCronExpression() {
		QuartzJobBean job = moduleService.getOne(18L);
		try {			
			Thread.sleep(11000);
			job.setCronExpression("*/5 * * * * ?");
			taskService.updateCronExpression(job);
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSetNextFireTime() throws SchedulerException {
		QuartzJobBean job = moduleService.getOne(18L);
		
		Calendar now = new GregorianCalendar();
		now.add(Calendar.SECOND, 21);
		job.setStartTime(now.getTime());
		System.out.println(now.toString());
		
		taskService.updateStartTime(job);
		
		try {
			Thread.sleep(30000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
