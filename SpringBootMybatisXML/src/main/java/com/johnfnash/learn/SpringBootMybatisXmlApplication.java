package com.johnfnash.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.johnfnash.learn.mapper") //Ìí¼Ó¶Ômapper°üÉ¨Ãè@MapperScan
public class SpringBootMybatisXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisXmlApplication.class, args);
	}
}
