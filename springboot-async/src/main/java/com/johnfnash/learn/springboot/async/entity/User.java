package com.johnfnash.learn.springboot.async.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class User {

	private Integer id;
	private String userName;
	
}
