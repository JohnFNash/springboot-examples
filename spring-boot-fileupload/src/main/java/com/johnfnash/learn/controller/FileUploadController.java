package com.johnfnash.learn.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

	private final static String UPLOADED_FOLDER = "D:\\";
	
	@GetMapping("/")
	public String index() {
		return "upload";
	}
	
	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file,
				RedirectAttributes redirectAttributes) {
		if(file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			 return "redirect:/uploadStatus";
		}
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			
			redirectAttributes.addFlashAttribute("message", 
					"You successfully uploaded '" + file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/uploadStatus";
	}
	
	@GetMapping
	public String uploadStatus() {
		return "uploadStatus";
	}
	
	@PostMapping("/multiupload")
	public String muitipleFileUpload(@RequestParam("file") MultipartFile[] files,
				RedirectAttributes redirectAttributes) {
		if(files.length == 0 || files[0].isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			 return "redirect:/uploadStatus";
		}
		
		try {
			byte[] bytes;
			Path path;
			StringBuilder b = new StringBuilder();
			b.append("You successfully uploaded ");
			for (MultipartFile file : files) {
				bytes = file.getBytes();
				path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
				Files.write(path, bytes);
				b.append("'")
				 .append(file.getOriginalFilename())
				 .append("',");
			}
			if(b.charAt(b.length()-1) == ',') {
				b.deleteCharAt(b.length()-1);
			}
			redirectAttributes.addFlashAttribute("message", b.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/uploadStatus";
	}
	
}
