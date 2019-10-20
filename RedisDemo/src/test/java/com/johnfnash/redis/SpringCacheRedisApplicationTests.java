package com.johnfnash.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnfnash.redis.domain.User;
import com.johnfnash.redis.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCacheRedisApplicationTests {
    private static final long USER_ID_TO_TEST = 1L;
    
    @Autowired
    private UserService userService;
    
    @Test
    public void testList() {
        System.out.println("first query...");
        System.out.println(userService.list());
        System.out.println("second query...");
        System.out.println(userService.list());
    }
    
    @Test
    public void testFindById() {
        System.out.println("first query...");
        System.out.println(userService.findUserById(USER_ID_TO_TEST));
        System.out.println("second query...");
        System.out.println(userService.findUserById(USER_ID_TO_TEST));
    }
    
    @Test
    public void testUpUser() {
        System.out.println(userService.upuser(USER_ID_TO_TEST));
        testFindById();
    }
    
    @Test
    public void update() {
        User user = userService.findUserById(USER_ID_TO_TEST);
        user.setName("test name...");
        userService.update(user);
        
        user = userService.findUserById(USER_ID_TO_TEST);
        System.out.println(user);
    }

}
