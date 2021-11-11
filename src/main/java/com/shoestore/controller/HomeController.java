package com.shoestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shoestore.entity.Quiz;
import com.shoestore.entity.User;
import com.shoestore.service.QuizService;
import com.shoestore.service.SecurityService;

@Controller
public class HomeController {
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private QuizService quizService;
	
	@GetMapping("/")
	public String index(Model model) {
		return "redirect:/login";
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		return "home";
	}
	
	@GetMapping("/quiz")
	public String quiz(Model model) {
		if(!securityService.isAuthenticated()) {
			return "login";
		}
		model.addAttribute("quiz", quizService.findAll());
		
		return "quiz";
		
	}
	
	@GetMapping("/view-quiz/{id}")
	public String viewQuiz(@PathVariable Long id, Model model) {
		if(!securityService.isAuthenticated()) {
			return "login";
		}
		String path = "/uploads/quiz/";
		model.addAttribute("qz",quizService.getQuizById(id));
		model.addAttribute("path",path);
		return "quiz-page";
	}

	
	@GetMapping("/reviews")
	public String review(Model model) {
		if(!securityService.isAuthenticated()) {
			return "login";
		}
		return "reviews";
		
	}
	@GetMapping("/result")
	public String result(Model model) {
		if(!securityService.isAuthenticated()) {
			return "login";
		}
		return "result";
		
	}
	
	@GetMapping("/add-new-quiz")
	public String addNewQuiz(Model model) {
		model.addAttribute("quizForm", new Quiz());
		return "addNewQuiz";
	}
	
	
}
