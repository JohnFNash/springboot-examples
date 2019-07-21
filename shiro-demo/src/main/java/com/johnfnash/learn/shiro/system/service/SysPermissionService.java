package com.johnfnash.learn.shiro.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnfnash.learn.shiro.system.mapper.SysPermissionMapper;

@Service
@Transactional(readOnly = true)
public class SysPermissionService {

	@Autowired
	private SysPermissionMapper permissionMapper;
	
	public List<String> findPermissionCodesByUser(String userId) {
		return permissionMapper.findPermissionCodesByUser(userId);
	}
	
}
