package com.example.controller;

import com.example.entity.Difficulty;
import com.example.entity.ToDo;
import com.example.entity.ToDoUser;
import com.example.service.ToDoService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SessionAttributes("loggedInUser")
@Controller
public class AuthController {
	@Autowired
	ToDoService todoService;

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new ToDoUser());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(ToDoUser user,Model model,HttpSession session) {
		if (userService.userExists(user.getUsername())) {
			model.addAttribute("error", "Username already taken. Please choose another.");
			model.addAttribute("user", new ToDoUser());
			return "register";
		}
		userService.registerNewUser(user);
		session.setAttribute("currentUser", user);
		return "redirect:/todos";
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username,
						@RequestParam String password,
						HttpSession session,
						Model model) {
		if (userService.login(username, password)) {
			ToDoUser user = userService.findByUsername(username);
			session.setAttribute("currentUser", user);
			return "redirect:/todos";
		}
		model.addAttribute("error", "Invalid username or password");
		return "login"; // Return to login page if authentication fails
	}

	@GetMapping("/todos")
	public String getTodos(Model model, HttpSession session) {
		ToDoUser user = (ToDoUser) session.getAttribute("currentUser");
		if (user == null) {
			return "redirect:/login"; // Redirect to login if user is not found
		}

		// Fetch active and completed tasks for the current user
		List<ToDo> activeTodos = todoService.getTodosByUserAndStatus(user, false); // Status false for active tasks
		List<ToDo> completedTodos = todoService.getTodosByUserAndStatus(user, true); // Status true for completed tasks

		// Calculate analytics
		int totalTasks = activeTodos.size() + completedTodos.size();
		int completedTasks = completedTodos.size();
		int easyTasks = (int) activeTodos.stream().filter(todo -> todo.getDifficulty() == Difficulty.EASY).count();
		int mediumTasks = (int) activeTodos.stream().filter(todo -> todo.getDifficulty() == Difficulty.MEDIUM).count();
		int hardTasks = (int) activeTodos.stream().filter(todo -> todo.getDifficulty() == Difficulty.HARD).count();

		// Calculate the percentage of completed tasks
		double completedPercentage = totalTasks == 0 ? 0 : (double) completedTasks / totalTasks * 100;

		// Add the tasks and analytics to the model
		model.addAttribute("activeTodos", activeTodos);
		model.addAttribute("completedTodos", completedTodos);
		model.addAttribute("currentUser", user);
		model.addAttribute("totalTasks", totalTasks);
		model.addAttribute("completedTasks", completedTasks);
		model.addAttribute("easyTasks", easyTasks);
		model.addAttribute("mediumTasks", mediumTasks);
		model.addAttribute("hardTasks", hardTasks);
		model.addAttribute("completedPercentage", completedPercentage);

		return "todos"; // Return the todos.html template
	}


	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // Invalidate the session
		return "redirect:/login"; // Redirect to login page
	}

}
   