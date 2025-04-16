package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

	
	    private final UserService userService;

	    public UserController(UserService userService) {
	        this.userService = userService;
	    }

	    @GetMapping("/users")
	    public String showUsers(Model model) {
	        model.addAttribute("users", userService.findAllUsers());
	        return "user-list";
	    }

	    @GetMapping("/register")
	    public String showRegistrationForm(Model model) {
	        model.addAttribute("user", new User());
	        return "register";
	    }

	    @PostMapping("/register")
	    public String registerUser(@ModelAttribute User user) {
	        userService.registerUser(user);
	        return "redirect:/login?registered=true";
	    }
	}
