package com.johnfnash.learn.springboot.async.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnfnash.learn.springboot.async.entity.User;
import com.johnfnash.learn.springboot.async.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getOne(Integer id) {
		return userMapper.getOne(id);
	}

	@Transactional
	@Override
	public void update(User user) {
		userMapper.update(user);
		
		throw new IllegalArgumentException("inputError");
	}

}
