package com.shoestore.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoestore.entity.Role;
import com.shoestore.entity.User;
import com.shoestore.entity.UserRole;
import com.shoestore.service.SecurityService;
import com.shoestore.service.UserService;
import com.shoestore.utility.MailConstructor;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if(securityService.isAuthenticated()) {
			return "redirect:/home";
		}
		
		if(error != null) {
			model.addAttribute("error", "Invalid Username or Password");
		}
		if(logout != null) {
			model.addAttribute("message", "You have been logged out successfully");
		}
		
		return "login";
	}
	
	
	@GetMapping("/registration")
	public String registration(Model model) {
		if(securityService.isAuthenticated()) {
			return "redirect:/home";
		}
		model.addAttribute("userForm", new User());
		return "create-account";
	}
	
	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, @RequestParam("roles") String uRole,
			BindingResult bindingResult, 
			Model model) throws Exception {
		userValidator.validate(userForm, bindingResult);
		int RLE = 2;
		String RLE_STR = "ROLE_USER";
		if(!bindingResult.hasErrors()) {
			if(uRole.equals("1")) {
				RLE = 1;
				RLE_STR = "ROLE_ADMIN";
			}
			Role role = new Role();
			role.setId(RLE);
			role.setName(RLE_STR);
			//Set <Role> roleSet = new HashSet<>();
			//roleSet.add(role);
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(userForm, role));
			String pwd = userForm.getPassword();
			userForm.setPassword(securityService.bCryptPasswordEncoder().encode(pwd));
			userService.createUser(userForm, userRoles);
			
			//userService.save(userForm);
			//securityService.autoLogin(userForm.getUsername(), userForm.getPassword());
			model.addAttribute("msg", "Your account has been successfully created. You can now login");
		}
		//return "redirect:/index";
		return "create-account";
	}
	
	
	@GetMapping("/forgot-password")
	public String forgotPassword(Model model) {
		return "forgot-password";
	}
	
	@PostMapping("/forgot-password")
	public String forgotPassword(HttpServletRequest request, 
			@ModelAttribute("email") String email,
			Model model) {
		 User usr = userService.findByEmail(email);
		 if(usr != null) {
			 String msg = "Kindly click on the link below to reset your password: http://localhost:8080";
			 String subject = "Sporty Shoes :  Password Reset";
			 SimpleMailMessage sender = mailConstructor.buildEmailSender(email, subject, msg);
			 mailSender.send(sender);
			 model.addAttribute("msg", "Instructions on how to reset your password has been sent to your email");
		 }else {
			 model.addAttribute("err", "Email does not exist");
		 }
		 return "forgot-password";
		
	}
	
	
	
}
