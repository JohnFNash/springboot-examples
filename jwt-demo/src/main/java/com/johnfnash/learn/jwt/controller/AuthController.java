package com.johnfnash.learn.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.johnfnash.learn.jwt.util.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

	 @RequestMapping(value="/login",method = RequestMethod.POST)
	 public String login(String loginName, String password) {
		 // 1. 进行账号、密码校验
		 
		 // 2. 校验通过之后
		 String userId = "adadsad";
		 String jwt = JwtUtils.createJWT(userId, loginName, 1800000);
		 return jwt;
	 }
	
}
