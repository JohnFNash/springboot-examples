package com.example.firstdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.firstdemo.entity.Book;

public interface ReadingListRepository extends JpaRepository<Book, Long> {

	List<Book> findByReader(String reader);
	
}
