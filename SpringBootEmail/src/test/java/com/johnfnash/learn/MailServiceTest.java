package com.johnfnash.learn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.johnfnash.learn.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

	@Autowired
	private MailService mailService;
	
	@Autowired
    private TemplateEngine templateEngine;
	
	@Test
	public void testSimpleMail() {
		mailService.sendSimpleMail("1605629895@qq.com","test simple mail"," hello this is simple mail");
	}
	
	@Test
	public void testHtmlMail() {
		String content="<html>\n" +
	            "<body>\n" +
	            "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
	            "</body>\n" +
	            "</html>";
		mailService.sendHtmlMail("1605629895@qq.com","test html mail", content);
	}

	@Test
	public void sendAttachmentsMail() {
		String filePath = "D:\\desktop\\12222222.jpg";
		mailService.sendAttachmentsMail("1605629895@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
	}
	
	@Test
	public void sendInlineResourceMail() {
		String rscId = "neo006";
	    String content="<html><body>这是有图片的邮件：<br/><img src=\'cid:" + rscId + "\' ></body></html>";
	    String imgPath = "D:\\desktop\\12222222.jpg";
	    
	    mailService.sendInlineResourceMail("1605629895@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
	}
	
	@Test
	public void sendTemplateMail() {
		//创建邮件正文
		Context context = new Context();
		context.setVariable("id", "006");
		
		String emailContent = templateEngine.process("emailTemplate", context);
		
		mailService.sendHtmlMail("1605629895@qq.com","主题：这是模板邮件",emailContent);
	}
	
}
