package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ToDoService;
import com.example.entity.ToDo;


@RestController
public class ToDoController {
	
	@Autowired
	ToDoService service;
	
	@GetMapping("/list")
	public List<ToDo> getList(){
		return service.getList();
	}
	
	@GetMapping("/list/{id}")
	public ToDo getListById(@PathVariable int id){
		return service.getListById(id);
	} 
	
	
	@PostMapping("/list/add")
	public void addTask(@RequestBody ToDo task) {
		service.addTask(task);
	}
	
	@DeleteMapping("/list/remove/{id}")
	public void removeTask(@PathVariable int id){
		service.delete(id);
	}
	
	@PutMapping("/list/update")
	public void updateList(@RequestBody ToDo task) {
		service.updateTask(task);
	}
	
} 
