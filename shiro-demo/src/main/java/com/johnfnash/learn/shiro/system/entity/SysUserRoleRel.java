package com.johnfnash.learn.shiro.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 用户角色关联表
 * 
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("T_SYS_USER_ROLE_REL")
public class SysUserRoleRel extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private String roleId;

	private String userId;

}