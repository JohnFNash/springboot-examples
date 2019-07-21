package com.johnfnash.learn.shiro.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 菜单表
 * 
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("T_SYS_MENU")
public class SysMenu extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private Boolean leaf;

	private String menuCode;

	private Integer menuLevel;

	private String menuName;

	private String menuUrl;

	private Integer orderNo;

	private String parentCode;

	private Integer state;
	
	public interface State{
		int ENABLED = 1;
		int DISABLED = 2;
	}
	
	private String icon;

}