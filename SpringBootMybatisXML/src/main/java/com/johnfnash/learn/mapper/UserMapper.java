package com.johnfnash.learn.mapper;

import java.util.List;

import com.johnfnash.learn.domain.UserEntity;

public interface UserMapper {

	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);
	
	void insert(UserEntity user);
	
	void update(UserEntity user);
	
	void delete(Long id);
}
