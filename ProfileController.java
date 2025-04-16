package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;

@Controller
public class ProfileController {
	


	    private final UserRepo userRepo;

	    public ProfileController(UserRepo userRepo) {
	        this.userRepo = userRepo;
	    }

	    @GetMapping("/profile")
	    public String viewProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
	        User user = userRepo.findByEmail(userDetails.getUsername())
	                            .orElseThrow(() -> new RuntimeException("User not found"));
	        model.addAttribute("user", user);
	        return "profile";
	    }
}