package com.johnfnash.learn.springboot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserBean {

	//用户名
    private String name;
    
    //密码
    private String password;
	
}
