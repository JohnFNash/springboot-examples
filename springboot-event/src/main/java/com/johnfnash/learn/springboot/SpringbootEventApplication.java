package com.johnfnash.learn.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
public class SpringbootEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootEventApplication.class, args);
	}
	
	@Bean
	public DataLoafer dataLoader() {
		return new DataLoafer();
	}
	
	@Slf4j
	static class DataLoafer implements CommandLineRunner {
		
		@Override
		public void run(String... args) throws Exception {
			log.info("Loading data...");
		}
		
	}

}
