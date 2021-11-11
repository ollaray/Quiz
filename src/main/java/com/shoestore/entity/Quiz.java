package com.shoestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="quiz")
public class Quiz {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String title;
	
	private String description;
	private String username;
	private String url;
	
	@Transient
	private MultipartFile document;
	
	public Quiz() {}

	public Quiz(Long id, String title, String description, User user, MultipartFile document) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.username = username;
		this.document = document;
	}

	public Quiz(String title, String description, User user, MultipartFile document) {
		super();
		this.title = title;
		this.description = description;
		this.username = username;
		this.document = document;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MultipartFile getDocument() {
		return document;
	}

	public void setDocument(MultipartFile document) {
		this.document = document;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
