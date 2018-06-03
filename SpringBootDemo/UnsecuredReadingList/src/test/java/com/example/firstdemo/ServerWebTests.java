package com.example.firstdemo;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ReadingListApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ServerWebTests {
	
	private static ChromeDriver browser;
	
	@Value("${local.server.port}")
	private int port;
	
	@BeforeClass
	public static void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		browser = new ChromeDriver();
		browser.manage().timeouts()
			   .implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public static void closeBrowser() {
		browser.quit();   // 关闭浏览器
	}
	
	@Test
	public void addBookToEmptyList() {
		String baseUrl = "http://localhost:" + port + "/reading/walt";
		browser.get(baseUrl); 	// 获取主页
		
		// 判断图书列表是否为空
		assertEquals("You have no books in your book list", 
				browser.findElementByTagName("div").getText());
		
		browser.findElementByName("title")
			   .sendKeys("BOOK TITLE");
		browser.findElementByName("author")
			   .sendKeys("BOOK AUTHOR");
		browser.findElementByName("isbn")
			   .sendKeys("1234567890");
		browser.findElementByName("description")
			   .sendKeys("DESCRIPTION");
		browser.findElementByTagName("form")
			   .submit(); 	// 填充并发送表单
		
		WebElement dl = browser.findElementByCssSelector("dt.bookHeadline");
		assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)", dl.getText());
		
		WebElement dt = browser.findElementByCssSelector("dd.bookDescription");
		assertEquals("DESCRIPTION", dt.getText());
	}
	
}
