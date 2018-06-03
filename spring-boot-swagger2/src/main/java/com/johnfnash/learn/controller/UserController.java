package com.johnfnash.learn.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.johnfnash.learn.domain.JsonResult;
import com.johnfnash.learn.domain.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

	// 创建线程安全的Map
	static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体 User", required = true)
	@PostMapping("user")
	public ResponseEntity<JsonResult> add (@RequestBody User user){
		JsonResult r = new JsonResult();
		
		try {
			users.put(user.getId(), user);
			r.setResult(user.getId());
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
	
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(r);
	}
	
	@ApiOperation(value="获取用户列表", notes="获取用户列表")
	@GetMapping("users")
	public ResponseEntity<JsonResult> getUserList (){
		JsonResult r = new JsonResult();
		
		try {
			List<User> userList = new ArrayList<User>(users.values());
			r.setResult(userList);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(r);
	}
	
}
