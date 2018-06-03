package com.example.firstdemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.firstdemo.dao.ReaderRepository;
import com.example.firstdemo.dao.ReadingListRepository;
import com.example.firstdemo.entity.AmazonProperties;
import com.example.firstdemo.entity.Book;
import com.example.firstdemo.entity.Reader;

@Controller
@RequestMapping("/reading")
public class ReadingListController {
	
	private ReadingListRepository readingListRepository;
	private ReaderRepository readerRepository;
	private AmazonProperties amazonProperties;
	
	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository,
				ReaderRepository readerRepository,
				AmazonProperties amazonProperties) {
		this.readingListRepository = readingListRepository;
		this.readerRepository = readerRepository;
		this.amazonProperties = amazonProperties;
	}
	
	@RequestMapping(value="/fail", method=RequestMethod.GET)
	public void fail() {
		throw new RuntimeException();
	}
	
	@ExceptionHandler(value=RuntimeException.class)
	@ResponseStatus(value=HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
	public String error() {
		return "error";
	}
	
	@RequestMapping(value="/{reader}", method=RequestMethod.GET)
	public String readersBooks(@PathVariable("reader") String reader, Model model) {
		List<Book> readingList = 
				readingListRepository.findByReader(reader);
		if(null != readingList) {
			model.addAttribute("books", readingList);
			model.addAttribute("reader", reader);
			model.addAttribute("amazonID", amazonProperties.getAssociateId()); // 将 associateId 放入模型
		}
		return "readingList";
	}
	
	@RequestMapping(value="/{reader}", method=RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/reading/{reader}";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String username, String password, Model model) {
	  	Optional<Reader> optReader = readerRepository.findById(username);		
	  	Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
		if(!optReader.isPresent() 
				|| ! encoder.matches(password, optReader.get().getPassword()) // 判断密码是否一致
			) {
			model.addAttribute("error", "Username or password is incorrect!");
			return "redirect:/login";
		}
		return "redirect:/reading/" + username;
	}
	
}
