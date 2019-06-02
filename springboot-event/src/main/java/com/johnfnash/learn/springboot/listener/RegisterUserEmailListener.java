package com.johnfnash.learn.springboot.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.johnfnash.learn.springboot.entity.UserBean;
import com.johnfnash.learn.springboot.event.UserRegisterEvent;

@Component
public class RegisterUserEmailListener {

	/**
	 * 发送邮件监听实现
	 * 
	 * @param userRegisterEvent 用户注册事件
	 */
	@EventListener
	@Async
	public void sendMail(UserRegisterEvent userRegisterEvent) {
		try {
			Thread.sleep(3000);// 静静的沉睡3秒钟
		} catch (Exception e) {
			e.printStackTrace();
		}

		//获取注册用户对象信息
        UserBean user = userRegisterEvent.getUser();
        System.out.println("用户："+user.getName()+"，注册成功，发送邮件通知。");
	}

}
