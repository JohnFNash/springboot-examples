package com.johnfnash.learn.service;

public interface UserService {

	void deleteByUserId(Long id);
	
	int modifyByIdAndUserId(String userName, Long id);
	
}
