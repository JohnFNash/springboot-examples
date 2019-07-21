package com.johnfnash.learn.shiro.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.johnfnash.learn.shiro.common.dto.PageInfo;
import com.johnfnash.learn.shiro.common.utils.PageInfoUtils;
import com.johnfnash.learn.shiro.system.entity.SysUser;
import com.johnfnash.learn.shiro.system.mapper.SysUserMapper;
import com.johnfnash.learn.shiro.system.vo.FindUserListReq;
import com.johnfnash.learn.shiro.system.vo.SysUserVo;

@Service
@Transactional(readOnly = true)
public class SysUserService {

	@Autowired
	private SysUserMapper userMapper; 
	
	public SysUser findUserByUserName(String userName) {
		return userMapper.findUserByUserName(userName);
	}
	
	public PageInfo<SysUserVo> list(FindUserListReq req) {
		Page<SysUser> page = new Page<>(req.getPageNo(), req.getPageSize());
        if(!StringUtils.isEmpty(req.getUserName())) {
            req.setUserName('%' + req.getUserName() + '%');
        }
		List<SysUser> users = userMapper.findAll(page, req);
		
		List<SysUserVo> resultList = new ArrayList<SysUserVo>();
		SysUserVo tmpVo;
		for (SysUser sysUser : users) {
			tmpVo = new SysUserVo();
			BeanUtils.copyProperties(sysUser, tmpVo);
			resultList.add(tmpVo);
		}
		return PageInfoUtils.toPageInfo(page, resultList);
	}
	
}
