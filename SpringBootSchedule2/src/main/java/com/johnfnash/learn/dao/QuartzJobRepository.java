package com.johnfnash.learn.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.johnfnash.learn.domain.QuartzJobBean;

@Repository
public interface QuartzJobRepository extends JpaRepository<QuartzJobBean, Long> {
	
	List<QuartzJobBean> findByJobStatus(String jobStatus);
	
	List<QuartzJobBean> findByJobStatusNot(String jobStatus);
	
	// 修改上一次执行时间和下一次执行时间
	@Modifying
	@Query("update QuartzJobBean j set j.previousTime = ?1, j.nextTime = ?2 where j.jobId = ?3")
	int modifyByIdAndTime(Date previousTime, Date nextTime, Long jobId);
	
	// 修改job状态
	@Modifying
	@Query("update QuartzJobBean j set j.jobStatus = ?1 where j.jobId = ?2")
	int modifyByStatus(String jobStatus, Long jobId);
	
}
