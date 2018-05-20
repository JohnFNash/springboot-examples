package com.johnfnash.learn;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.johnfnash.learn.domain.UserEntity;
import com.johnfnash.learn.enums.UserSexEnum;
import com.johnfnash.learn.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testInsert() throws Exception {
		userMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
		userMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
		userMapper.insert(new UserEntity("cc", "c123456", UserSexEnum.WOMAN));
		
		Assert.assertEquals(3, userMapper.getAll().size());
	}
	
	@Test
	public void testQuery() {
		List<UserEntity> users = userMapper.getAll();
		System.out.println(users.toString());
	}
	
	@Test
	public void testUpdate() throws Exception {
		UserEntity user = userMapper.getOne(28L);
		System.out.println(user);
		
		user.setNickName("neo");
		userMapper.update(user);
		
		Assert.assertTrue("neo".equals(userMapper.getOne(28L).getNickName()));
	}

}
