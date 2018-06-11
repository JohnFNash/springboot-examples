package com.johnfnash.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnfnash.learn.domain.People;
import com.johnfnash.learn.repository.PeopleRepository;

@Service
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	private PeopleRepository peopleRepository;
	
	@Override
	public People save(People people) {
		return peopleRepository.save(people);
	}

	@Override
	public People getOne(Long id) {
		return peopleRepository.getOne(id);
	}

	@Override
	public People findById(Long id) {
		return peopleRepository.findOne(id);
	}

	@Override
	public List<People> findAll() {
		return peopleRepository.findAll();
	}

}
