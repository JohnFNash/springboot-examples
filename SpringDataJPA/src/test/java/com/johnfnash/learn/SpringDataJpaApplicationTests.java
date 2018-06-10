package com.johnfnash.learn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnfnash.learn.domain.User;
import com.johnfnash.learn.repository.UserRepository;
import com.johnfnash.learn.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataJpaApplicationTests {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testBaseQuery() {
		User user1 = new User("aa1", "aa@126.com", "aa", "aa123456", "05/19/2018");
		User user2 = new User("bb2", "bb@126.com", "bb", "bb123456", "05/19/2018");
		User user3 = new User("cc3", "cc@126.com", "cc", "cc123456", "05/19/2018");
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		
		userRepository.findAll();
		userRepository.getOne(1L);
		userRepository.delete(user2);
		userRepository.count();
		userRepository.existsById(1L);
	}
	
	@Test
	public void testUserDefinedSimpleQuery() {
		userRepository.findByUserName("aa");
		
		userRepository.findByUserNameOrEmail("aa", "bb@126.com");
		
		userRepository.countByUserName("cc");
		
		userRepository.findByEmailLike("@126");
		
		userRepository.findByUserNameOrderByEmailDesc("aa");
		
		userRepository.findByUserNameStartingWith("c");
	}
	
	@Test
	public void testComplicatedQuery() {
		int page = 0, size = 10;
		Sort sort = new Sort(Direction.DESC, "id");
		Pageable pageable = PageRequest.of(page, size, sort);
		userRepository.findAll(pageable);
		userRepository.findByUserName("aa", pageable);
		
		userRepository.findTopByOrderByUserNameDesc();
		userRepository.findFirst10ByUserName("aa", sort);
	}
	
	@Test
	public void testManualQuery() {
		userService.modifyByIdAndUserId("ca", 1L);
		//userRepository.deleteByUserId(2L);
		userService.deleteByUserId(2L);
	}

}
