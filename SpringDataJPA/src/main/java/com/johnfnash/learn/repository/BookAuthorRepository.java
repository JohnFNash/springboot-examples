package com.johnfnash.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnfnash.learn.domain.BookAuthor;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {

}
