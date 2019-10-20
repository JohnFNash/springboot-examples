package com.johnfnash.redis.service;

import java.util.List;

import com.johnfnash.redis.domain.User;

public interface UserService {

	List<User> list();  
	  
    User findUserById(Long id);  
  
    void update(User user);  
  
    void remove(Long id);  
  
    User upuser(Long id);  
    
    User saveUser(User user);
	
}
