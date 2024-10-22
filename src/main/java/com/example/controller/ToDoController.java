package com.example.controller;

import com.example.entity.ToDo;
import com.example.entity.ToDoUser;
import com.example.service.ToDoService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/list")
public class ToDoController {

	@Autowired
	private ToDoService todoService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String getList(Model model, HttpSession session) {
		ToDoUser user = (ToDoUser) session.getAttribute("currentUser");
		if (user == null) {
			return "redirect:/login"; // Redirect to login if user is not found
		}

		List<ToDo> todos = todoService.getTodosByUser(user);
		model.addAttribute("todos", todos);
		return "todos";
	}

	@PostMapping("/add")
	public String addTask(@RequestParam String content, HttpSession session) {
		ToDoUser user = (ToDoUser) session.getAttribute("currentUser");
		if (user == null) {
			return "redirect:/login";
		}

		ToDo task = new ToDo();
		task.setContent(content);
		task.setStatus(Boolean.FALSE);
		task.setUser(user);
		todoService.addTask(task);
		return "redirect:/todos";
	}

	@PostMapping("/update")
	public String updateTask(@RequestParam Long id, @RequestParam String content, @RequestParam Boolean status) {
		todoService.updateTask(id, content, status); // Update method adjusted accordingly
		return "redirect:/todos";  // Redirect to the list view
	}

	@GetMapping("/remove/{id}")
	public String removeTask(@PathVariable Long id) {
		todoService.deleteTask(id);
		return "redirect:/todos";
	}
}
