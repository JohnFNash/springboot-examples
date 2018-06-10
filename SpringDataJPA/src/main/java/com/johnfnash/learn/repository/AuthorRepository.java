package com.johnfnash.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnfnash.learn.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
