package com.johnfnash.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnfnash.learn.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
