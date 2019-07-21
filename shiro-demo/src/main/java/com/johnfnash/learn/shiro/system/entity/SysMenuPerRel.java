package com.johnfnash.learn.shiro.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 菜单权限关联表
 * 
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("T_SYS_MENU_PER_REL")
public class SysMenuPerRel extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private String menuId;

	private String perId;

}