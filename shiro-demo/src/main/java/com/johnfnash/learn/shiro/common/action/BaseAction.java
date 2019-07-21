package com.johnfnash.learn.shiro.common.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.johnfnash.learn.shiro.system.entity.SysUser;

public class BaseAction {
	
	protected String getLoginUserId() {
		return getPrincipal().getId();
	}
	
	protected Subject getSubject() {
		Subject subject = SecurityUtils.getSubject();
		return subject;
	}

	protected SysUser getPrincipal() {
		return (SysUser) getSubject().getPrincipal();
	}
	
	protected Session getSession() {
		return getSubject().getSession();
	}

	protected Session getSession(boolean flag) {
		return getSubject().getSession(flag);
	}
}
