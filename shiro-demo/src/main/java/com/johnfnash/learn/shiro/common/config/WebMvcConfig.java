package com.johnfnash.learn.shiro.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Value("${spring.config.location:classpath:/}")
	private String springConfigLocation;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// swagger-bootstrap-ui
		registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

		// 数据导入Excel模板文件
		StringBuilder sb = new StringBuilder(springConfigLocation);
		if (!springConfigLocation.endsWith("/")) {
			sb.append("/");
		}
		if (!springConfigLocation.contains("/config")) {
			sb.append("config/");
		}
		sb.append("template/");
		registry.addResourceHandler("impTmpls/*").addResourceLocations(sb.toString());

	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
				.allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH").allowCredentials(true).maxAge(3600);
	}

	
}
