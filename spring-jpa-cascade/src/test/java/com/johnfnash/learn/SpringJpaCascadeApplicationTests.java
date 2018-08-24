package com.johnfnash.learn;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnfnash.learn.domain.Article;
import com.johnfnash.learn.domain.Author;
import com.johnfnash.learn.repository.ArticleRepository;
import com.johnfnash.learn.repository.AuthorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJpaCascadeApplicationTests {
	
	@SuppressWarnings("unused")
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Test
	public void contextLoads() {
		Author author = new Author();
		author.setName("xxxxxx");
		
		Article article1 = new Article("t1", "xxx", author);
		Article article2 = new Article("t2", "yyy", author);
		author.setArticleList(Arrays.asList(article1, article2));
		authorRepository.save(author);
	}

}
