package com.johnfnash.learn.shiro.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 角色权限关联表
 * 
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("T_SYS_ROLE_PER_REL")
public class SysRolePerRel extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private String perId;

	private String roleId;

}