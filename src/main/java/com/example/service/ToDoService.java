package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.ToDo;
import com.example.repository.TodoRepo;

@Service
public class ToDoService {
	
	@Autowired
	TodoRepo repo;

	public List<ToDo> getList() {
		return repo.findAll();
	}

	public ToDo getListById(int id) {
		return repo.findById(id).orElse(new ToDo());
	}
	
	public void addTask(ToDo task) {
		repo.save(task);
	}

	public void delete(int id) {
		repo.deleteById(id);
		
	}

	public void updateTask(ToDo task) {
		repo.save(task);
		
	}
	
	
	
}
