package com.johnfnash.learn.service.primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnfnash.learn.dao.primary.UserRepository;
import com.johnfnash.learn.entity.primary.User;

@Service
@Transactional("transactionManagerPrimary")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAllUserByPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
        Page<User> users =  this.userRepository.findAll(pageable);
        return users.getContent();
	}

	@Override
	public User updateUser(User user, boolean throwEx) {
		User userNew = this.userRepository.save(user);
		if (throwEx) {
			throw new RuntimeException("throw a ex");
		}
		return userNew;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteUser(id);
	}

}
