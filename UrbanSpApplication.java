package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.example.demo")
public class UrbanSpApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UrbanSpApplication.class, args);
		
		System.out.println("Table created");
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashed = encoder.encode("Admin9386@");
        System.out.println(hashed);
	}

}
