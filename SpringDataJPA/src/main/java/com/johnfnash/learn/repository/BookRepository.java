package com.johnfnash.learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.johnfnash.learn.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT b.id, b.name, GROUP_CONCAT(a.name) as authorName from book b, author a, book_author ba"
			+ " where b.id = ba.book_id and a.id = ba.author_id and b.name like ?1 group by b.id, b.name")
    List<Object[]> findByNameContaining(String name);
	
}
