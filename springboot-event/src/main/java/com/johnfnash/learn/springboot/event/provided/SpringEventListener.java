package com.johnfnash.learn.springboot.event.provided;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.johnfnash.learn.springboot.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SpringEventListener {

	@EventListener
//	@Async
	public void onApplicationEvent(SpringApplicationEvent event) {
        log.info("......SpringApplicationEvent......");
    }
	
	@EventListener
//	@Async
	public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        log.info("......ApplicationEnvironmentPreparedEvent......");
    }
	
	@EventListener
//	@Async
	public void onApplicationEvent(ApplicationPreparedEvent event) {
        log.info("......ApplicationPreparedEvent......");
    }
	
	@EventListener
//	@Async
	public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("......ApplicationStartedEvent......");
    }
	
	@EventListener
//	@Async
	public void onApplicationEvent(ApplicationReadyEvent event) {
		ConfigurableApplicationContext context = event.getApplicationContext();
		UserService userService = (UserService) context.getBean("userService");
		log.info("userService: " + userService.toString());
        log.info("......ApplicationReadyEvent......");
    }
	
	
}
