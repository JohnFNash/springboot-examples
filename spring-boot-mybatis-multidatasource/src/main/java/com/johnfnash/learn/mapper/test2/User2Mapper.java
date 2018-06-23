package com.johnfnash.learn.mapper.test2;

import java.util.List;

import com.johnfnash.learn.domain.UserEntity;

public interface User2Mapper {

	List<UserEntity> getAll();

	UserEntity getOne(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);

}
