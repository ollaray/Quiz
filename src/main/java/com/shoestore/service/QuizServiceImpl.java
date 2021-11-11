package com.shoestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoestore.entity.Quiz;
import com.shoestore.repository.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService {
	@Autowired
	private QuizRepository quizRepository;
	
	@Override
	public Quiz save(Quiz quiz) {
		return quizRepository.save(quiz);
	}
	
	@Override
	public List<Quiz> findAll() {
		return quizRepository.findAll();
	}
	
	public Quiz findByTitle(String title) {
		return quizRepository.findByTitle(title);
	}
	
	public Quiz findByUsername(String username) {
		return quizRepository.findByUsername(username);
	}
	
	public Quiz findByUrl(String url) {
		return quizRepository.findByUrl(url);
	}

	@Override
	public Quiz getQuizById(Long id) {
		// TODO Auto-generated method stub
		return quizRepository.findById(id).get();
	}
	

}
