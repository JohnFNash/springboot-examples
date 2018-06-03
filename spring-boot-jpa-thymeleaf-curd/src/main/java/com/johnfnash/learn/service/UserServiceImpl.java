package com.johnfnash.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnfnash.learn.domain.User;
import com.johnfnash.learn.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;	
	
	@Override
	public List<User> getUserList() {
		return userRepository.findAll();
	}

	@Override
	public User findUserById(long id) {
		return userRepository.getOne(id);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void edit(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(long id) {
		userRepository.deleteById(id);
	}

}
