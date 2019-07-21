package com.johnfnash.learn.shiro.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 角色表
 * 
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("T_SYS_ROLE")
public class SysRole extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private String descs;

	private String roleName;

	private Integer state;
	
	public interface State{
		int ACTIVE = 1;
		int INACTIVE = 2;
	}

}