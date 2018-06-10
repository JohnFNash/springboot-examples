package com.johnfnash.learn;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnfnash.learn.domain.Author;
import com.johnfnash.learn.domain.Book;
import com.johnfnash.learn.domain.BookAuthor;
import com.johnfnash.learn.repository.AuthorRepository;
import com.johnfnash.learn.repository.BookAuthorRepository;
import com.johnfnash.learn.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTests {

	@Autowired
    private BookRepository bookRepository;
	
    @Autowired
    private AuthorRepository authorRepository;
	
    @Autowired
    private BookAuthorRepository bookAuthorRepository;
    
	@Before
    public void init() {
        Author lewis = new Author("Lewis");
        Author mark = new Author("Mark");
        Author peter = new Author("Peter");
        authorRepository.save(lewis);
        authorRepository.save(mark);
        authorRepository.save(peter);

        Book spring = new Book("Spring in Action");
        Book springboot = new Book("Spring Boot in Action");
        bookRepository.save(spring);
        bookRepository.save(springboot);
        
        bookAuthorRepository.save(new BookAuthor(spring.getId(), lewis.getId()));
        bookAuthorRepository.save(new BookAuthor(spring.getId(), mark.getId()));
        bookAuthorRepository.save(new BookAuthor(springboot.getId(), mark.getId()));
        bookAuthorRepository.save(new BookAuthor(springboot.getId(), peter.getId()));
    }
	
	@After
	public void deleteAll() {
		bookAuthorRepository.deleteAll();
		bookRepository.deleteAll();
		authorRepository.deleteAll();
	}
	
	@Test
	public void findAll() {
		assertEquals(bookRepository.findAll().size(), 2);
		assertEquals(authorRepository.findAll().size(), 3);
		
		List<Object[]> books = bookRepository.findByNameContaining("Spring%");
		for (Object[] book : books) {
			for (Object object : book) {
				System.out.print(object + ", ");
			}
			System.out.println();
		}
	}
	
}
