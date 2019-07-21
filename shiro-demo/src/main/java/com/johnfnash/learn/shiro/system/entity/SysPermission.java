package com.johnfnash.learn.shiro.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 权限表
 * 
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("T_SYS_PERMISSION")
public class SysPermission extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private String descs;

	private String perCode;

	private String perName;

	private Integer state;
	
	public interface State{
		int ACTIVE = 1;
		int INACTIVE = 2;
	}

}