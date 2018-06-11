package com.johnfnash.learn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnfnash.learn.domain.Authority;
import com.johnfnash.learn.domain.User;
import com.johnfnash.learn.repository.AuthorityRepository;
import com.johnfnash.learn.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
    private UserRepository userRepository;
	
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Test
    public void saveUser() {
    	Authority authority = new Authority("ROLE_ADMIN");
   	 	authorityRepository.save(authority);
    	
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        
        List<Authority> authorityList = new ArrayList<Authority>();
        authorityList.add(authority);
        
        user.setAuthorityList(authorityList);
        userRepository.save(user);
    }    
    
    @Test
    public void deleteUser() {
        userRepository.deleteById(1L);
    }
	
    @Test
    public void queryUser() {
		User user = userRepository.findOne(2L);
		System.out.println(user);
		
		System.out.println(user.getAuthorityList());
	}
    
}
