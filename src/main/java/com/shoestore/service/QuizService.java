package com.shoestore.service;

import java.util.List;

import com.shoestore.entity.Quiz;

public interface QuizService {
	Quiz save(Quiz quiz);
	List<Quiz> findAll();
	Quiz getQuizById(Long id);
}
