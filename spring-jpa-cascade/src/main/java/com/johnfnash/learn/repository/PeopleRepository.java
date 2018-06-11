package com.johnfnash.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.johnfnash.learn.domain.People;

public interface PeopleRepository extends JpaRepository<People, Long> {
	
	@Query("select p from People p join fetch p.address")
	public People findOne(Long id);

}
