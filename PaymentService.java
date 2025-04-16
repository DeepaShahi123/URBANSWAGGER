package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.entity.Payment;
import com.example.demo.repo.PaymentRepository;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepo;

    public PaymentService(PaymentRepository paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    public Payment processPayment(Order order, String mode) {
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMode(mode);
        payment.setPaymentStatus("SUCCESS"); // Simulated status
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepo.save(payment);
    }
    public Payment save(Payment payment) {
        return paymentRepo.save(payment);
    }

}
