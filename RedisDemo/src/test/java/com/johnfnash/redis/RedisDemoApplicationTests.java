package com.johnfnash.redis;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnfnash.redis.domain.User;
import com.johnfnash.redis.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemoApplicationTests {
	@Autowired
	private UserService service;
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void manuaulTest() {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}
	
	@Test
	public void selectTest() {
		System.out.println("===========第一次调用=======");
		@SuppressWarnings("unused")
		List<User> list = service.list();
		System.out.println("===========第二次调用=======");
		List<User> list2 = service.list();
		for (User u : list2) {
			System.out.println("u = " + u);
		}
	}
	
	@Test
	public void saveTest() {
		User user = new User();
		user.setId(4L);
		user.setName("xxxx");
		user.setPassword("123456");
		service.saveUser(user);
	}

}
