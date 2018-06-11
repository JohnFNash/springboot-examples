package com.johnfnash.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnfnash.learn.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
