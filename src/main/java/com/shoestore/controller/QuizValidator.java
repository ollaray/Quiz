package com.shoestore.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.shoestore.entity.Quiz;

@Component
public class QuizValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Quiz.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
	Quiz quiz = (Quiz) o;
		
		ValidationUtils.rejectIfEmpty(errors, "title", "NotEmpty");
		if(quiz.getTitle() == null) {
			errors.rejectValue("title", "Title field is compulsory");
		}
		
		ValidationUtils.rejectIfEmpty(errors, "description", "NotEmpty");
		if(quiz.getDescription() == null) {
			errors.rejectValue("description", "Description field is compulsory");
		}
		
		ValidationUtils.rejectIfEmpty(errors, "document", "NotEmpty");
		if(quiz.getDocument() == null) {
			errors.rejectValue("document", "Quiz document field is compulsory");
		}
		
	}


}
