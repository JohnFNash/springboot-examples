package com.johnfnash.learn.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnfnash.learn.dao.QuartzJobRepository;
import com.johnfnash.learn.domain.QuartzJobBean;

@Service("moduleService")
public class QuartzJobServiceImpl implements QuartzJobService {

	@Autowired
	private QuartzJobRepository repository;
	
	@Override
	public List<QuartzJobBean> findAll() {
		return repository.findAll();
	}

	@Transactional
	@Override
	public QuartzJobBean save(QuartzJobBean jobBean) {
		return repository.save(jobBean);
	}

	@Override
	public QuartzJobBean getOne(long jobId) {
		return repository.getOne(jobId);
	}

	@Transactional
	@Override
	public int modifyByIdAndTime(Date previousTime, Date nextTime, Long jobId) {
		return repository.modifyByIdAndTime(previousTime, nextTime, jobId);
	}

	
	
	@Override
	public List<QuartzJobBean> findByJobStatus(String jobStatus) {
		return repository.findByJobStatus(jobStatus);
	}

	@Override
	public List<QuartzJobBean> findByJobStatusNot(String jobStatus) {
		return repository.findByJobStatusNot(jobStatus);
	}

	@Transactional
	@Override
	public int modifyByStatus(String jobStatus, Long jobId) {
		return repository.modifyByStatus(jobStatus, jobId);
	}

}
