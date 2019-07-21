package com.johnfnash.learn.shiro.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnfnash.learn.shiro.system.mapper.SysRoleMapper;

@Service
@Transactional(readOnly = true)
public class SysRoleService {

	@Autowired
	private SysRoleMapper roleMapper;
	
	public List<String> findRolesByUserId(String userId) {
		return roleMapper.findRolesByUserId(userId);
	}
	
}
