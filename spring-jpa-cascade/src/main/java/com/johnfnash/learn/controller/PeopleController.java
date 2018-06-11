package com.johnfnash.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnfnash.learn.domain.People;
import com.johnfnash.learn.service.PeopleService;

@RestController
@RequestMapping("/user")
public class PeopleController {

	@Autowired
	private PeopleService peopleService;
	
	@GetMapping("/list")
	public List<People> list() {
		return peopleService.findAll();
	}
	
}
