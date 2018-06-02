package com.johnfnash.learn.service;

import java.util.Date;
import java.util.List;

import com.johnfnash.learn.domain.QuartzJobBean;

public interface QuartzJobService {

	public List<QuartzJobBean> findAll();
	
	public List<QuartzJobBean> findByJobStatusNot(String jobStatus);
	
	public List<QuartzJobBean> findByJobStatus(String jobStatus);
	
	public QuartzJobBean save(QuartzJobBean jobBean);
	
	public QuartzJobBean getOne(long jobId);
	
	public int modifyByIdAndTime(Date previousTime, Date nextTime, Long jobId);
	
	public int modifyByStatus(String jobStatus, Long jobId);
	
}
