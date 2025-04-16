package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.CartService;

@Controller
public class MyPageController {

	private final CartService cartService;

	public MyPageController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping("/mypage")
	public String showMyPage(Model model) {
		model.addAttribute("cartItems", cartService.getCartItems());
		return "mypage"; // Must match the Thymeleaf file name in templates/
	}
}
