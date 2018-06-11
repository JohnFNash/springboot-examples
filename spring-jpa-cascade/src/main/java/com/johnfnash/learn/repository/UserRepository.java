package com.johnfnash.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.johnfnash.learn.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("from User u join fetch u.authorityList")
	public User findOne(Long id);
	
}
