package com.johnfnash.learn.mybatisplus.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.johnfnash.learn.mybatisplus.entity.User;
import com.johnfnash.learn.mybatisplus.mapper.UserMapper;
import com.johnfnash.learn.mybatisplus.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
