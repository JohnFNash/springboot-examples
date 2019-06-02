package com.johnfnash.learn.springboot.async.service;

import com.johnfnash.learn.springboot.async.entity.User;

public interface UserService {

	User getOne(Integer id);
	
	void update(User user);
	
}
