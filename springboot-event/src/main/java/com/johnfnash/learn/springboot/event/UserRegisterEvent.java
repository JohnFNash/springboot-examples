package com.johnfnash.learn.springboot.event;

import org.springframework.context.ApplicationEvent;

import com.johnfnash.learn.springboot.entity.UserBean;

import lombok.Getter;

@Getter
public class UserRegisterEvent extends ApplicationEvent {

	private static final long serialVersionUID = 9095574899118364438L;

	//注册用户对象
    private UserBean user;
	
    /**
     * 重写构造函数
     * @param source 发生事件的对象
     * @param user 注册用户对象
     */
	public UserRegisterEvent(Object source, UserBean user) {
		super(source);
		this.user = user;
	}

}
