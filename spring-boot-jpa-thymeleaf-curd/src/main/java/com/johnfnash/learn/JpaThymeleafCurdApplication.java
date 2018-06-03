package com.johnfnash.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// 启动类继承自SpringBootServletInitializer方可正常部署至常规tomcat下，其主要能够起到web.xml的作用
@SpringBootApplication
public class JpaThymeleafCurdApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JpaThymeleafCurdApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaThymeleafCurdApplication.class, args);
	}
}
