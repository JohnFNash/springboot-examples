package com.example.firstdemo;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import com.example.firstdemo.dao.ReadingListRepository;
import com.example.firstdemo.entity.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ReadingListApplication.class)
public class ReadingListRepositoryTest {
	@Autowired
	private ReadingListRepository repo;
	
	@Test
	@Transactional
	public void saveABook() throws Exception {
		assertEquals(0, repo.findAll().size());
		
		Book book = new Book();
		book.setTitle("TITLE");
	    book.setDescription("DESCRIPTION");
	    book.setAuthor("AUTHOR");
	    book.setIsbn("1234567890");
	    book.setReader("reader");  
	    Book saved = repo.save(book);
	    
	    Book found = repo.getOne(saved.getId());
	    assertEquals(saved.getId(), found.getId());
	    assertEquals("DESCRIPTION", found.getDescription());
	    assertEquals("AUTHOR", found.getAuthor());
	    assertEquals("1234567890", found.getIsbn());
	    assertEquals("reader", found.getReader());
	}
	
}
