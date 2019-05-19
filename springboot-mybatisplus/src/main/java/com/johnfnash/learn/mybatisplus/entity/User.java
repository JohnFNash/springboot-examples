package com.johnfnash.learn.mybatisplus.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("tb_user")
public class User {
	
	//新增时会自动设置主键ID的值
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	// 如果属性名与数据库的字段名一致或者可以转换（驼峰转下划线...）,可以不添加下面的注解
	//@TableField(value = "name")
    private String userName;

	private String password;
    
	public User() {
	}

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}
	
}
