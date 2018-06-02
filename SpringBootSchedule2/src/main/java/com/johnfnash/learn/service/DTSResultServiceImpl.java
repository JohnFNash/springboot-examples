package com.johnfnash.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnfnash.learn.dao.DTSResultRepository;
import com.johnfnash.learn.domain.DTSResult;

@Service("dtsResultService")
public class DTSResultServiceImpl implements DTSResultService {

	@Autowired
	private DTSResultRepository repository;
	
	@Override
	public DTSResult save(DTSResult result) {
		return repository.save(result);
	}

}
