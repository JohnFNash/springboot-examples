package com.example.firstdemo;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.firstdemo.entity.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ReadingListApplication.class) // 此处为 xxxApplication.class 
@WebAppConfiguration	// 开启web上下文
public class MockMvcWebTests {
	@Autowired
	private WebApplicationContext webContext;
	
	private MockMvc mockMvc;
	
	private final String READER = "craig";
	
	@Before
	public void setupMockMvc() {
		// 设置 MockMvc
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
								 .build();
	}
	
	@Test
	public void homePage() throws Exception {
		mockMvc.perform(get("/reading" + READER))
			.andExpect(status().isOk())
			.andExpect(view().name("readingList"))
			.andExpect(model().attributeExists("books"))
			.andExpect(model().attribute("books", Matchers.empty()));
	}
	
	@Test
	public void postBook() throws Exception {
		// 执行 Post 请求，提交一本新书
		mockMvc.perform(post("/reading/" + READER)
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.param("title", "BOOK TITLE")
							.param("author", "BOOK AUTHOR")
							.param("isbn", "1234567890")
							.param("description", "DESCRIPTION"))
				.andExpect(status().is3xxRedirection())
				.andExpect(header().string("location", "/reading/" + READER));
		
		Book expectedBook = new Book();
		expectedBook.setId(1L);
		expectedBook.setReader(READER);
		expectedBook.setTitle("BOOK TITLE");
		expectedBook.setAuthor("BOOK AUTHOR");
		expectedBook.setIsbn("1234567890");
		expectedBook.setDescription("DESCRIPTION");
		
		mockMvc.perform(get("/reading/" + READER))
			   .andExpect(status().isOk())
			   .andExpect(view().name("readingList"))
			   .andExpect(model().attributeExists("books"))
			   .andExpect(model().attribute("books", hasSize(1)))
			   .andExpect(model().attribute("books", contains(samePropertyValuesAs(expectedBook))));
	}
	
}
