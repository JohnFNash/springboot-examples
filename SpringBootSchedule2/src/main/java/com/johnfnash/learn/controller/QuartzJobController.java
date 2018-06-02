package com.johnfnash.learn.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.johnfnash.learn.domain.QuartzJobBean;
import com.johnfnash.learn.service.QuartzJobService;
import com.johnfnash.learn.service.TaskService;

@Controller
@RequestMapping("/job")
public class QuartzJobController {

	@Autowired
	private QuartzJobService jobService;
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/list")
	public String getJobList(Model model) {
		List<QuartzJobBean> jobList = jobService.findByJobStatusNot(QuartzJobBean.STATUS_DELETED);
		model.addAttribute("jobs", jobList);
		
		return "jobList";
	}
	
	@PutMapping("/{id}/status")
	@ResponseBody
	public List<QuartzJobBean> updateJobStatus(@PathVariable("id") Long id, @RequestParam("jobStatus") String jobStatus, Model model) {
		jobService.modifyByStatus(jobStatus, id);
		QuartzJobBean jobBean = jobService.getOne(id);
		
		List<QuartzJobBean> jobs;
		try {
			jobs = taskService.getAllJobs();
			if(QuartzJobBean.STATUS_RUNNING.equals(jobBean.getJobStatus()) && !jobs.contains(jobBean)) {
				taskService.addJob(jobBean);
			} else if(QuartzJobBean.STATUS_RUNNING.equals(jobBean.getJobStatus())  && jobs.contains(jobBean)) {
				taskService.resumeJob(jobBean);
			}
			
			if(QuartzJobBean.STATUS_NOT_RUNNING.equals(jobBean.getJobStatus()) && jobs.contains(jobBean)) {
				taskService.pauseJob(jobBean);
			} else if(QuartzJobBean.STATUS_NOT_RUNNING.equals(jobBean.getJobStatus()) && !jobs.contains(jobBean)) {
				jobService.modifyByStatus(jobBean.getJobStatus(), jobBean.getJobId());
			}
		} catch (SchedulerException e1) {
			e1.printStackTrace();
		}
		
		List<QuartzJobBean> jobList = jobService.findByJobStatusNot(QuartzJobBean.STATUS_DELETED);
		return jobList;
	}
	
	@PutMapping("/{id}/date")
	@ResponseBody
	public Map<String, String> updateNextRunDate(@PathVariable("id") Long id, @RequestParam("date") String date, Model model) {
		QuartzJobBean jobBean = jobService.getOne(id);
		if(jobBean != null && date != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startTime;
			try {
				startTime = df.parse(date);
				jobBean.setStartTime(startTime);
				jobBean.setNextTime(startTime);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			jobService.save(jobBean);
		}
			
		Map<String, String> result = new HashMap<String, String>();
		result.put("status", "success");
		return result;
	}
	
}
