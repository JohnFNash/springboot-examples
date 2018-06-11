package com.johnfnash.learn.service;

import java.util.List;

import com.johnfnash.learn.domain.People;

public interface PeopleService {

	public People save(People address);
	
	public People getOne(Long id);
	
	public People findById(Long id);
	
	public List<People> findAll();
	
}
