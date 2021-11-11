package com.shoestore.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.shoestore.entity.Quiz;
import com.shoestore.service.QuizService;
import com.shoestore.service.SecurityService;

@Controller
public class QuizController {
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private QuizValidator quizValidator;
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/add-new-quiz")
	public String addQuiz(@ModelAttribute("quizForm") Quiz quizForm,
			BindingResult bindingResult, 
			HttpServletRequest request, 
			Model model) {
		//check for errors
		quizValidator.validate(quizForm, bindingResult);
		if(bindingResult.hasErrors()) {
			model.addAttribute("error", "Invalid input(s)");
		}else {
			//Quiz Service
			String usn = securityService.currentUsername();
			
			
			quizForm.setUsername(usn);
		
			MultipartFile doc = quizForm.getDocument();
			
			String name = "";
			
			 try {
				 byte[] bytes = doc.getBytes();
				 name = quizForm.getId()+".json";
				 BufferedOutputStream bOS = 
						 new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/uploads/quiz/"+name)));
				 bOS.write(bytes);
				 bOS.close();
				 
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
			quizForm.setUrl(name);
			quizService.save(quizForm);
			model.addAttribute("msg", "Congratulations, your quiz was succefully added");
		}
		return "addNewQuiz";
	}
	
	

}
