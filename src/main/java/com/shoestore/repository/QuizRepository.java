package com.shoestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoestore.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>{
	public List<Quiz> findAll();
	public Quiz findById(int id);
	public Quiz findByTitle(String title);
	public Quiz findByUsername(String username);
	public Quiz findByUrl(String url);
	
	
}
