package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class CartItem {

	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	    @JoinColumn(name = "product_id")
	    private Product product;

	    private int quantity;

	    private String username;
	    
	   
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
		
		//new code
		  public double getPrice() {
		        return product.getPrice() * quantity;
		    }
		  //new code end
	    // Getters and setters
	}
