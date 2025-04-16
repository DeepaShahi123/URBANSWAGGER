package com.example.demo.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;

@Service
public class CartService {
    private final List<CartItem> cart = new ArrayList<>();

    public void addToCart(Product product) {
        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(1);
        cart.add(item);
    }

    public List<CartItem> getCartItems() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }
}
