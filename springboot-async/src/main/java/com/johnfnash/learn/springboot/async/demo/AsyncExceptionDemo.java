package com.johnfnash.learn.springboot.async.demo;

import java.util.UUID;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.johnfnash.learn.springboot.async.entity.User;
import com.johnfnash.learn.springboot.async.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AsyncExceptionDemo {

	@Autowired
	private UserService userService;
	
	@Async
	public void simple() {
		log.info("this is a void method");
//		hardDemo("asasa");
	}
	
	@Async
	@Transactional
	public void inputDemo(String s) {
		log.info("this is a input method,sss{}", s);
		
		User user = userService.getOne(1);
		user.setUserName(UUID.randomUUID().toString());
		userService.update(user);
		
		throw new IllegalArgumentException("error");
	}
	
	@Async
	public Future<String> hardDemo(String s) {
		log.info("this is a hard method,{}", s);
		Future<String> future;
		try {
			Thread.sleep(3000);
			throw new IllegalArgumentException();
		} catch (InterruptedException e) {
			future = new AsyncResult<String>("InterruptedException error");
		} catch (IllegalArgumentException e) {
            future = new AsyncResult<String>("i am throwing IllegalArgumentException error");
		}
		return future;
	}
	
}
