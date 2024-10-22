package com.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ToDoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<ToDo> todos;

    public ToDoUser(Long id, String username, String password, List<ToDo> todos) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.todos = todos;
    }

    public ToDoUser(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ToDo> getTodos() {
        return todos;
    }

    public void setTodos(List<ToDo> todos) {
        this.todos = todos;
    }
}
