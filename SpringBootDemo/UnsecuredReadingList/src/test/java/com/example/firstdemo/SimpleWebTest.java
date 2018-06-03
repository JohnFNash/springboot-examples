package com.example.firstdemo;

import static org.assertj.core.api.Assertions.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ReadingListApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SimpleWebTest {

	@Value("${local.server.port}")
	private int port;
	
	@Test(expected = HttpClientErrorException.class)
	public void pageNotFound( ) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getForObject("http://localhost:{port}/bogusPage", String.class, port);
			fail("Should result in HTTP 404");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
			throw e;
		}
	}
	
	@Test
	public void testRestTemplate() throws Exception {
		TestRestTemplate rest = new TestRestTemplate();
	    String s = rest.getForObject("http://localhost:{port}/bogusPage", String.class, port);
	    System.out.println(s);
	}
	
}
