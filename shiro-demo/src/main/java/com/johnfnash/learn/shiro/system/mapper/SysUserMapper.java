package com.johnfnash.learn.shiro.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.johnfnash.learn.shiro.system.entity.SysUser;
import com.johnfnash.learn.shiro.system.vo.FindUserListReq;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

	@Select("select * from T_SYS_USER where LOGIN_NAME = #{userName}")
	public SysUser findUserByUserName(@Param("userName") String userName);
	
	public List<SysUser> findAll(Page<SysUser> page, @Param("req") FindUserListReq req);
	
}
