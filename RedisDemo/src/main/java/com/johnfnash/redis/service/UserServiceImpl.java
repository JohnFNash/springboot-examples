package com.johnfnash.redis.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.johnfnash.redis.domain.Info;
import com.johnfnash.redis.domain.User;

@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

	private Map<Long, User> userMap = new HashMap<>();  
    private Map<Long, Info> infoMap = new HashMap<>();
	
    public UserServiceImpl() {  
        User u1=new User();  
        u1.setId(1L);  
        u1.setName("1111");  
        u1.setPassword("11223434");  
        User u2=new User();  
        u2.setId(2L);  
        u2.setName("1111");  
        u2.setPassword("11223434");  
        User u3=new User();  
        u3.setId(3L);  
        u3.setName("1111");  
        u3.setPassword("11223434");  
          
        userMap.put(1L,u1);  
        userMap.put(2L,u2);  
        userMap.put(3L,u3);  
        infoMap.put(1L, new Info("18559198715", "¸£ÖÝÊÐ"));  
    }
    
    @Cacheable()
	@Override
	public List<User> list() {
    	System.out.println("quering list.....");
    	User[] users = new User[userMap.size()];
    	this.userMap.values().toArray(users);
		return Arrays.asList(users);
	}

	@Override
	@Cacheable(value = "user", key="'user'.concat(#id.toString())")
	public User findUserById(Long id) {
		return userMap.get(id);
	}

	@Override
	@Cacheable(value = "info", key="'info'.concat(#id.toString())")
	public Info findInfoById(Long id) {
		return infoMap.get(id);
	}

	@Override
	@Cacheable(value = "user", key="'user'.concat(#id.toString())")
	public void update(User user) {
		userMap.put(user.getId(), user);
	}

	@Override
	@CacheEvict(value = "user", key = "'user'.concat(#id.toString())")
	public void remove(Long id) {
		userMap.remove(id);
	}

	@Override
	@CacheEvict(value = "user", key = "'user'.concat(#id.toString())") 
	public User upuser(Long id) {
		User d= userMap.get(id);  
        d.setName("000000000000000000000000000000000000000000000000");  
        return d;  
	}

	@CachePut(key = "\"user_\" + #user.id")
	@Override
	public User saveUser(User user) {
		userMap.put(user.getId(), user);
		return user;
	}

}
