package com.johnfnash.learn.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnfnash.learn.domain.DTSResult;
import com.johnfnash.learn.domain.QuartzJobBean;
import com.johnfnash.learn.service.DTSResultService;
import com.johnfnash.learn.service.QuartzJobService;
import com.johnfnash.learn.util.SpringUtils;
import com.johnfnash.learn.util.TaskUtils;

/**     
 *  Job有状态实现类，不允许并发执行     
 * 	若一个方法一次执行不完下次轮转时则等待该方法执行完后才执行下一次操作     
 * 	主要是通过注解：@DisallowConcurrentExecution
 */   
@DisallowConcurrentExecution     
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail job = context.getJobDetail();
		JobKey key = job.getKey();
		String jobIdentity = "scheduleJob" + key.getGroup() + "_" + key.getName();
		Trigger trigger = context.getTrigger();
		QuartzJobBean scheduleJob = (QuartzJobBean) context.getMergedJobDataMap().get(jobIdentity);
		logger.info("运行任务名称 = [" + scheduleJob + "]");
		
		try {
			DTSResult result = TaskUtils.invokMethod(scheduleJob);
			
			scheduleJob.setNextTime(trigger.getNextFireTime());
			scheduleJob.setPreviousTime(trigger.getPreviousFireTime());
			
			QuartzJobService jobService = SpringUtils.getBean("moduleService");
			jobService.modifyByIdAndTime(scheduleJob.getPreviousTime(), scheduleJob.getNextTime(), scheduleJob.getJobId());
			
			// 写入运行结果
			DTSResultService dtsService = SpringUtils.getBean("dtsResultService");
			dtsService.save(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
}
