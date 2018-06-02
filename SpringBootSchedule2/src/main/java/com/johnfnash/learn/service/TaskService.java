package com.johnfnash.learn.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.johnfnash.learn.dao.QuartzJobRepository;
import com.johnfnash.learn.domain.QuartzJobBean;
import com.johnfnash.learn.jobs.QuartzJobFactory;
import com.johnfnash.learn.jobs.QuartzJobFactoryDisallowConcurrentExecution;
import com.johnfnash.learn.util.TaskUtils;

@Service
public class TaskService {

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired     
	private SchedulerFactoryBean schedulerFactoryBean;   
	
	@Autowired
	private QuartzJobRepository repository;
	
	/**    
	 * 获取单个任务    
	 * @param jobName    
	 * @param jobGroup    
	 * @return    
	 * @throws SchedulerException    
	 */    
	public QuartzJobBean getJob(String jobName,String jobGroup) throws SchedulerException {
		QuartzJobBean job = null;    
		Scheduler scheduler = getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);    
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		if (null != trigger) {
			job = createJob(jobName, jobGroup, scheduler, trigger);
		}
		
		return job;
	}

	private Scheduler getScheduler() {
		return schedulerFactoryBean.getScheduler();
	}

	private QuartzJobBean createJob(String jobName, String jobGroup, Scheduler scheduler, Trigger trigger)
			throws SchedulerException {
		QuartzJobBean job;
		job = new QuartzJobBean();
		job.setJobName(jobName);    
		job.setJobGroup(jobGroup);    
		job.setDescription("触发器:" + trigger.getKey()); 
		job.setNextTime(trigger.getNextFireTime());
		job.setPreviousTime(trigger.getPreviousFireTime());
		
		Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
		job.setJobStatus(triggerState.name());
		
		if(trigger instanceof CronTrigger) {
			CronTrigger cronTrigger = (CronTrigger)trigger;
			String cronExpression = cronTrigger.getCronExpression();
			job.setCronExpression(cronExpression);
		}
		return job;
	}
	
	/**    
	 * 获取所有任务    
	 * @return    
	 * @throws SchedulerException    
	 */    
	public List<QuartzJobBean> getAllJobs() throws SchedulerException{   
		Scheduler scheduler = getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<QuartzJobBean> jobList = new ArrayList<QuartzJobBean>();
		List<? extends Trigger> triggers;
		QuartzJobBean job;
		for (JobKey jobKey : jobKeys) {
			triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				job = createJob(jobKey.getName(), jobKey.getGroup(), scheduler, trigger);
		        jobList.add(job);
			}
		}
		
		return jobList;
	}
	
	/**    
	 * 所有正在运行的job    
	 *     
	 * @return    
	 * @throws SchedulerException    
	 */    
	public List<QuartzJobBean> getRunningJob() throws SchedulerException {
		Scheduler scheduler = getScheduler();
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<QuartzJobBean> jobList = new ArrayList<QuartzJobBean>(executingJobs.size());
		QuartzJobBean job;
		JobDetail jobDetail;
		JobKey jobKey;
		
		for (JobExecutionContext executingJob : executingJobs) {
			jobDetail = executingJob.getJobDetail();
			jobKey = jobDetail.getKey();
			
			job = createJob(jobKey.getName(), jobKey.getGroup(), scheduler, executingJob.getTrigger());
			jobList.add(job);
		}
		
		return jobList;
	}
	
	/**    
	 * 添加任务    
	 *     
	 * @param scheduleJob    
	 * @throws SchedulerException    
	 */    
	public boolean addJob(QuartzJobBean job) throws SchedulerException { 
		if(job == null || !QuartzJobBean.STATUS_RUNNING.equals(job.getJobStatus())) {
			return false;
		}
		
		String jobName = job.getJobName();
		String jobGroup = job.getJobGroup();
		if(!TaskUtils.isValidExpression(job.getCronExpression())) {
			logger.error("时间表达式错误（"+jobName+","+jobGroup+"）, "+job.getCronExpression());    
			return false;
		} else {
			Scheduler scheduler = getScheduler();
			// 任务名称和任务组设置规则：    // 名称：task_1 ..    // 组 ：group_1 ..
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName,	jobGroup);
			Trigger trigger = scheduler.getTrigger(triggerKey);
			// 不存在，创建一个       
			if (null == trigger) { 
				// 表达式调度构建器
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
				// 按新的表达式构建一个新的trigger
				trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
													 .startAt(job.getStartTime()==null ? (new Date()) : job.getStartTime()) // 设置job不早于这个时间进行运行,和调用trigger的setStartTime方法效果一致
													 .withSchedule(scheduleBuilder).build();
				
				//是否允许并发执行
				JobDetail jobDetail = getJobDetail(job);
				// 将 job 信息存入数据库
				job.setStartTime(trigger.getStartTime());
				job.setNextTime(trigger.getNextFireTime());
				job.setPreviousTime(trigger.getPreviousFireTime());
				job = repository.save(job);
				jobDetail.getJobDataMap().put(getJobIdentity(job), job);
				
				scheduler.scheduleJob(jobDetail, trigger);
				
			} else { // trigger已存在，则更新相应的定时设置  
				// 更新 job 信息到数据库
				job.setStartTime(trigger.getStartTime());
				job.setNextTime(trigger.getNextFireTime());
				job.setPreviousTime(trigger.getPreviousFireTime());
				job = repository.save(job);
				getJobDetail(job).getJobDataMap().put(getJobIdentity(job), job);
				
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
				// 按新的表达式构建一个新的trigger
				trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
													 .startAt(job.getStartTime()==null ? (new Date()) : job.getStartTime()) // 设置job不早于这个时间进行运行,和调用trigger的setStartTime方法效果一致
													 .withSchedule(scheduleBuilder).build();
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		}
		return true;
	}

	private String getJobIdentity(QuartzJobBean job) {
		return "scheduleJob"+(job.getJobGroup() +"_"+job.getJobName());
	}

	private JobDetail getJobDetail(QuartzJobBean job) {
		Class<? extends Job> clazz = QuartzJobBean.CONCURRENT_IS.equals(job.isConcurrent()) 
						? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;		
		JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
		return jobDetail;
	}
	
	/**    
	 * 暂停任务    
	 * @param job    
	 * @return    
	 */ 
	@Transactional
	public boolean pauseJob(QuartzJobBean job){    
		Scheduler scheduler = getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
		boolean result;
		try {
			scheduler.pauseJob(jobKey);
			
			// 更新任务状态到数据库
			job.setJobStatus(QuartzJobBean.STATUS_NOT_RUNNING);
			repository.modifyByStatus(job.getJobStatus(), job.getJobId());
			
			result = true;
		} catch (SchedulerException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	/**    
	 * 恢复任务    
	 * @param job    
	 * @return    
	 */    
	@Transactional
	public boolean resumeJob(QuartzJobBean job){
		Scheduler scheduler = getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
		boolean result;
		try {
			logger.info("resume job : " + (job.getJobGroup() + "_" + job.getJobName()));
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
											.startAt(job.getStartTime()==null ? (new Date()) : job.getStartTime()) // 设置job不早于这个时间进行运行,和调用trigger的setStartTime方法效果一致
											.withSchedule(scheduleBuilder).build();
			scheduler.rescheduleJob(triggerKey, trigger);
			scheduler.resumeJob(jobKey);
			
			// 更新任务状态到数据库
			job.setJobStatus(QuartzJobBean.STATUS_RUNNING);
			repository.modifyByStatus(job.getJobStatus(), job.getJobId());
			
			result = true;
		} catch (SchedulerException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	/**    
	 * 删除任务    
	 */    
	@Transactional
	public boolean deleteJob(QuartzJobBean job){ 
		Scheduler scheduler = getScheduler();    
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());    
		boolean result;
		try{    
			scheduler.deleteJob(jobKey);
			
			// 更新任务状态到数据库
			job.setJobStatus(QuartzJobBean.STATUS_DELETED);
			repository.modifyByStatus(job.getJobStatus(), job.getJobId());
						
			result = true;    
		} catch (SchedulerException e) {	
			result = false;
			e.printStackTrace();
		}    
		return result;    
	} 
	
	/**    
	 * 立即执行一个任务    
	 * @param scheduleJob    
	 * @throws SchedulerException    
	 */    
	public void startJob(QuartzJobBean scheduleJob) throws SchedulerException{
		Scheduler scheduler = getScheduler();  
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.triggerJob(jobKey);
	}
	
	/**    
	 * 更新任务时间表达式    
	 * @param job    
	 * @throws SchedulerException    
	 */    
	@Transactional
	public void updateCronExpression(QuartzJobBean job) throws SchedulerException {
		Scheduler scheduler = getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
		//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		//表达式调度构建器    
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
		//按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		//按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);
		
		// 更新 job 信息到数据库
		job.setStartTime(trigger.getStartTime());
		job.setNextTime(trigger.getNextFireTime());
		job.setPreviousTime(trigger.getPreviousFireTime());
		job = repository.save(job);
		getJobDetail(job).getJobDataMap().put(getJobIdentity(job), job);
	}
	
	/**
	 * 设置job的开始schedule时间
	 * @param job
	 * @throws SchedulerException
	 */
	@Transactional
	public void updateStartTime(QuartzJobBean job) throws SchedulerException {
		Scheduler scheduler = getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
		//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
		trigger.setStartTime(job.getStartTime());
		//按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);
		
		// 更新 job 信息到数据库
		job.setStartTime(trigger.getStartTime());
		job.setNextTime(trigger.getNextFireTime());
		job.setPreviousTime(trigger.getPreviousFireTime());
		job = repository.save(job);
		getJobDetail(job).getJobDataMap().put(getJobIdentity(job), job);
	}
	
}
