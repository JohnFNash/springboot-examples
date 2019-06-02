package com.johnfnash.learn.springboot.async.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.johnfnash.learn.springboot.async.entity.User;

public interface UserMapper {

	@Select("select * from tb_user where id = #{id}")
	@Results({
		@Result(property = "userName", column = "user_name")
	})
	User getOne(Integer id);
	
	@Update("update tb_user set user_name = #{userName} where id = #{id}")
	void update(User user);
	
}
