package com.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class ToDo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String content;
	private Boolean status=Boolean.FALSE;
	private LocalDateTime dueDate;

	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;


	@ManyToOne
	@JoinColumn(name = "user_id")
	private ToDoUser user;  // Reference to the User entity

	public ToDo() {
	}


	public ToDo(Long id, String content, Boolean status, LocalDateTime dueDate, Difficulty difficulty, ToDoUser user) {
		this.id = id;
		this.content = content;
		this.status = status;
		this.dueDate = dueDate;
		this.difficulty = difficulty;
		this.user = user;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public ToDoUser getUser() {
		return user;
	}

	public void setUser(ToDoUser user) {
		this.user = user;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
}

