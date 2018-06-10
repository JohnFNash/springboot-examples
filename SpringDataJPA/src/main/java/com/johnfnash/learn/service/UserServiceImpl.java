package com.johnfnash.learn.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnfnash.learn.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public void deleteByUserId(Long id) {
		userRepository.deleteByUserId(id);
	}

	@Transactional
	@Override
	public int modifyByIdAndUserId(String userName, Long id) {
		return userRepository.modifyByIdAndUserId(userName, id);
	}

}
