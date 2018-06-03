package com.johnfnash.learn.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.johnfnash.learn.domain.User;
import com.johnfnash.learn.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
    UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "redirect:list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<User> users = userService.getUserList();
		model.addAttribute("users", users);
		return "user/list";
	}
	
	@GetMapping("/toAdd")
	public String toAdd() {
		return "user/userAdd";
	}
	
	@PostMapping("/add")
	public String add(User user) {
		userService.save(user);
		return "redirect:/user/list";
	}
	
	@GetMapping("/toEdit")
	public String toEdit(Model model, Long id) {
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		return "user/userEdit";
	}
	
	@PostMapping("/edit")
	public String edit(User user) {
		userService.edit(user);
		return "redirect:/user/list";
	}
	
	@GetMapping("/delete")
	public String delete(Long id) {
		userService.delete(id);
		return "redirect:/user/list";
	}
	
}
