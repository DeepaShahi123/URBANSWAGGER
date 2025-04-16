package com.example.demo.controller;


import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/place")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order-form"; // this is your shipping form page
    }

    @PostMapping("/place")
    public String processOrder(@ModelAttribute Order order) {
        Order savedOrder = orderService.placeOrder(order);
        return "redirect:/payment/" + savedOrder.getId();
    }
    
    @GetMapping("/admin/orders")
    public String viewOrders(Model model) {
        List<Order> orders = orderService.getAllOrders(); // should fetch orders with payment data
        model.addAttribute("orders", orders);
        return "admin-orders";
    }
}
