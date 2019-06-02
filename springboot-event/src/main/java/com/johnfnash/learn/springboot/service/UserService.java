package com.johnfnash.learn.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.johnfnash.learn.springboot.entity.UserBean;
import com.johnfnash.learn.springboot.event.UserRegisterEvent;

@Service
public class UserService {

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * 用户注册方法
	 * 
	 * @param user
	 */
	public void register(UserBean user) {
		// ../省略其他逻辑
		
		//发布UserRegisterEvent事件
		applicationContext.publishEvent(new UserRegisterEvent(this, user));
	}

}
