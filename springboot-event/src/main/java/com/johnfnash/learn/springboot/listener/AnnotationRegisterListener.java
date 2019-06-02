package com.johnfnash.learn.springboot.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.johnfnash.learn.springboot.entity.UserBean;
import com.johnfnash.learn.springboot.event.UserRegisterEvent;

// 使用 @EventListener 方法实现注册事件监听
@Component
public class AnnotationRegisterListener {

	/**
     * 注册监听实现方法
     * @param userRegisterEvent 用户注册事件
     */
	@EventListener
	public void register(UserRegisterEvent event) {
		//获取注册用户对象
        UserBean user = event.getUser();

        //../省略逻辑
        
        //输出注册用户信息
        System.out.println("@EventListener注册信息，用户名："+user.getName()+"，密码："+user.getPassword());
	}
	
}
