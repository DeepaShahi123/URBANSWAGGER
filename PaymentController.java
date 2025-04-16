package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.entity.Payment;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    // Show the payment options page for a specific order
    @GetMapping("/{orderId}")
    public String showPaymentPage(@PathVariable Long orderId, Model model) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return "redirect:/error"; // Redirect to error page if order not found
        }
        model.addAttribute("order", order);
        return "payment-page"; // Your payment selection page (Thymeleaf view)
    }

    // Handle payment confirmation
    @PostMapping("/confirm")
    public String confirmPayment(@RequestParam Long orderId,
                                 @RequestParam String method,
                                 Model model) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return "redirect:/error"; // Error handling
        }

        // Save payment info to DB
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMode(method);
        payment.setPaymentStatus("SUCCESS"); // Simulated for now
        payment.setPaymentDate(LocalDateTime.now());

        paymentService.save(payment); // Save payment

        model.addAttribute("method", method);
        model.addAttribute("order", order);
        return "payment-success"; // Your success page
    }
}
