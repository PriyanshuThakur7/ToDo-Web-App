package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.ToDoUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.ToDo;
import com.example.repository.TodoRepo;

@Service
public class ToDoService {

	@Autowired
	TodoRepo repo;

	public List<ToDo> getTodosByUser(ToDoUser user) {
		return repo.findByUser(user);
	}

	public void addTask(ToDo task) {
		repo.save(task);
	}

	@Transactional
	public void deleteTask(Long id) {
		repo.deleteById(id);
	}

	public void updateTask(Long id, String newContent, Boolean newStatus) {
		Optional<ToDo> optionalTask = repo.findById(id);
		if (optionalTask.isPresent()) {
			ToDo task = optionalTask.get();
			task.setContent(newContent);
			task.setStatus(newStatus);
			repo.save(task);
		}
	}
}
