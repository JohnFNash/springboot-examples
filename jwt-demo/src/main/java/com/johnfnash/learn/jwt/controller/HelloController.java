package com.johnfnash.learn.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnfnash.learn.jwt.dto.JsonResult;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@GetMapping("detail")
	public JsonResult<String> hello() {
		return JsonResult.of("hello world!");
	}
	
}
