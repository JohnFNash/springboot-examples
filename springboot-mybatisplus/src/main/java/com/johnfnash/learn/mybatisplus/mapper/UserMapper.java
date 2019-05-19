package com.johnfnash.learn.mybatisplus.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.johnfnash.learn.mybatisplus.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
