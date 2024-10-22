package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table
public class ToDo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String content;
	private Boolean status=Boolean.FALSE;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private ToDoUser user;  // Reference to the User entity

	public ToDo() {
	}

	public ToDo(Long id, String content, Boolean status, ToDoUser user) {
		this.id = id;
		this.content = content;
		this.status = status;
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
}
