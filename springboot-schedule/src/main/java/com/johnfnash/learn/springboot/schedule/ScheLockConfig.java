package com.johnfnash.learn.springboot.schedule;

import java.time.Duration;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.ScheduledLockConfiguration;
import net.javacrumbs.shedlock.spring.ScheduledLockConfigurationBuilder;

@Component
public class ScheLockConfig {

	@Resource
	private DataSource dataSource;
	
	@Bean
	public LockProvider lockProvider(DataSource dataSource) {
		return new JdbcTemplateLockProvider(dataSource);
	}
	
	@Bean
	public ScheduledLockConfiguration scheduledLockConfiguration(LockProvider lockProvider) {
		return ScheduledLockConfigurationBuilder.withLockProvider(lockProvider)
												.withPoolSize(10)
												.withDefaultLockAtMostFor(Duration.ofMinutes(10))
												.build();
	}
	
}
