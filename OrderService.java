package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Order;

public interface OrderService {
	  Order placeOrder(Order order);
	    Order findById(Long id);
	    
	    //new code
	    
	    
	    List<Order> getAllOrders();

	    //
}



