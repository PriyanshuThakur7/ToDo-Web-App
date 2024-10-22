package com.example.controller;

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
	public String registerUser(ToDoUser user) {
		userService.registerNewUser(user);
		return "redirect:/login";
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

		List<ToDo> todos = todoService.getTodosByUser(user);
		model.addAttribute("todos", todos);
		model.addAttribute("currentUser", user);
		return "todos"; // Return the todos.html template
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // Invalidate the session
		return "redirect:/login"; // Redirect to login page
	}

}
   