package com.johnfnash.learn.service.primary;

import java.util.List;

import com.johnfnash.learn.entity.primary.User;

public interface UserService {

	public User findById(Long id);
	
    public User findByUsername(String username);

    public List<User> findAllUserByPage(int page, int size);

    public User updateUser(User user, boolean throwEx);

    public void deleteUser(Long id);
	
}
