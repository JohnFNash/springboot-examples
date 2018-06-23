package com.johnfnash.learn;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnfnash.learn.domain.UserEntity;
import com.johnfnash.learn.mapper.test1.User1Mapper;
import com.johnfnash.learn.mapper.test2.User2Mapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisMultidatasourceApplicationTests {

	@Autowired
    private User1Mapper user1Mapper;

	@Autowired
	private User2Mapper user2Mapper;
	
	@Test
	public void getUsers() {
		List<UserEntity> users = user1Mapper.getAll();
		System.out.println("------------- user list ---------");
		for (UserEntity user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void getUser() {
		UserEntity user = user2Mapper.getOne(29L);
		System.out.println(user);
	}
	
}
