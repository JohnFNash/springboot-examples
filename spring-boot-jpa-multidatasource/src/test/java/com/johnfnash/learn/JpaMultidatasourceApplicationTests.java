package com.johnfnash.learn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnfnash.learn.entity.primary.User;
import com.johnfnash.learn.entity.secondary.Order;
import com.johnfnash.learn.service.primary.UserService;
import com.johnfnash.learn.service.secondary.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaMultidatasourceApplicationTests {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Test
	public void testGetUser() {
		User user = userService.findById(2L);
		System.out.println(user);
	}
	
	@Test
	public void testGetOrder() {
		Order order = orderService.findByNumber("2018062109180244");
		System.out.println(order);
	}
	

}
