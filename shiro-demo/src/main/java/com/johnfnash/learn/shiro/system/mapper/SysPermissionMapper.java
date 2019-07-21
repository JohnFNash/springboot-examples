package com.johnfnash.learn.shiro.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.johnfnash.learn.shiro.system.entity.SysPermission;

@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

	public List<String> findPermissionCodesByUser(@Param("userId") String userId);
	
}
