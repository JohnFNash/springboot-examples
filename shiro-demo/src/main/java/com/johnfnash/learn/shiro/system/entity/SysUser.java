package com.johnfnash.learn.shiro.system.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 系统用户表
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@TableName("T_SYS_USER")
public class SysUser extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -7263855509985914106L;

	private String loginName;

	private String orgCode;

	private String passwd;

	private String phone;

	private Integer state;
	
	public interface State{
		int ACTIVE = 1;
		int INACTIVE = 2;
		int DELETED = 3; // 用户不能物理删除，只能逻辑删除。可能有业务表中存在该用户的id
	}

	private String userName;

	private Integer userType;
	
	public interface UserType{
		int SUPER_ADMIN = 1;
		int SYSTEM_USER = 2;
	}
	
	// 用户所有角色值，用于shiro做角色权限的判断
	private Set<String> roles = Collections.synchronizedSet(new HashSet<String>(0));

	// 用户所有权限值，用于shiro做资源权限的判断
	private Set<String> stringPermissions = Collections.synchronizedSet(new HashSet<String>(0));

}