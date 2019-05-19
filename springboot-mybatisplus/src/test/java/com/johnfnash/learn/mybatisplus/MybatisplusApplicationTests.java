package com.johnfnash.learn.mybatisplus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.johnfnash.learn.mybatisplus.entity.User;
import com.johnfnash.learn.mybatisplus.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusApplicationTests {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testAdd() {
		User user = new User("aaa", "rrrrr");
        boolean isSuccess = userService.insert(user);
        if(isSuccess){
            System.out.println("新增用户成功。");
        }else{
            System.out.println("新增用户失败。");
        }
	}
	
	@Test
	public void testList() {
		List<User> list = userService.selectList(null);
		System.out.println(list);
	}
	
	@Test
	public void testListByName() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_name", "xxx");
		List<User> list = userService.selectByMap(params);
		System.out.println(list);
	}
	
	@Test
	public void testGetById() {
		System.out.println(userService.selectById(1));
	}
	
	@Test
	public void testListByPage() {
		Page<User> page = new Page<>(1, 2);
		EntityWrapper<User> wrapper = new EntityWrapper<>();
		wrapper.like("user_name", "%");
		Page<User> page2 = userService.selectPage(page, wrapper);
		System.out.println(page2.getRecords());
	}

	@Test
	public void testUpdate() {
//		User user = userService.selectById(1);
//		user.setPassword("pwd");
//		userService.updateById(user);
		
		// updateById 只会修改设置了值的字段，没设置值的字段不会修改
		User user = new User();
		user.setId(1);
		user.setPassword("pwd");
		userService.updateById(user);
	}
	
}
