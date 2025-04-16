
	
	package com.example.demo.controller;

	import com.example.demo.service.OrderService;
	import com.example.demo.entity.Order;
import com.example.demo.repo.OrderRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

	@Controller
	@RequestMapping("/admin")
	public class AdminOrderController {

	    @Autowired
	    private OrderService orderService;
	    
	    @Autowired
	    private OrderRepo orderrepo;

	    @GetMapping("/orders")
	    public String viewOrders(Model model) {
	        List<Order> orders = orderService.getAllOrders();
	        model.addAttribute("orders", orders);
	        return "admin-orders"; // name of the Thymeleaf template
	    }
	    
	    
	    // new code
	    
	    @GetMapping("/orders/details/{id}")
	    @ResponseBody
	    public ResponseEntity<Order> getOrderDetails(@PathVariable Long id) {
	        Optional<Order> orderOpt = orderrepo.findById(id);
	        return orderOpt.map(ResponseEntity::ok)
	                       .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    
	    
	    
	    @GetMapping("/orders/{id}")
	    public String showOrderDetails(@PathVariable Long id, Model model) {
	        Optional<Order> orderOpt = orderrepo.findById(id);
	        if (orderOpt.isPresent()) {
	            Order order = orderOpt.get();
	            model.addAttribute("order", order);
	            model.addAttribute("items", order.getItems()); // If order has items
	            return "admin-order-details"; // Thymeleaf page to render
	        } else {
	            return "redirect:/admin/orders?error=notfound";
	        }
	    }
// new code end
	}



