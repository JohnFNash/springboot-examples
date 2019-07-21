package com.johnfnash.learn.shiro.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.johnfnash.learn.shiro.system.entity.SysRole;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

	public List<String> findRolesByUserId(@Param("userId") String userId);
	
}
