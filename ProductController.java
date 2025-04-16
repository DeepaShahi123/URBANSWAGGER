

/*import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/products")
    public String viewProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            Model model,
            Principal principal) {

    	if (principal != null) {
    	    String email = principal.getName(); // 👈 This is where 'email' is defined
    	    User user = userService.findByEmail(email)
    	            .orElseThrow(() -> new RuntimeException("User not found"));
    	    model.addAttribute("user", user);
    	}

        // 🔍 Filtered product list
        List<Product> allProducts = productService.getAllProducts();

        if (keyword != null && !keyword.isEmpty()) {
            allProducts = allProducts.stream()
                    .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (category != null && !category.isEmpty()) {
            allProducts = allProducts.stream()
                    .filter(p -> category.equalsIgnoreCase(p.getCategory()))
                    .collect(Collectors.toList());
        }

        if (brand != null && !brand.isEmpty()) {
            allProducts = allProducts.stream()
                    .filter(p -> brand.equalsIgnoreCase(p.getBrand()))
                    .collect(Collectors.toList());
        }

        // 📦 Unique categories and brands for dropdowns
        List<String> categories = productService.getAllCategories();
        List<String> brands = productService.getAllBrands();

        // 📤 Add to model
        model.addAttribute("products", allProducts);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);

        return "products"; // This is your products.html
    }
    
    
}
*/


package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepo;
import com.example.demo.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String listProducts(Model model,
                               @RequestParam(required = false) String category,
                               @RequestParam(required = false) String brand,
                               @RequestParam(required = false) String keyword) {

        List<Product> products;

        if ((category != null && !category.isEmpty()) || (brand != null && !brand.isEmpty()) || (keyword != null && !keyword.isEmpty())) {
            products = productRepo.findByCategoryContainingIgnoreCaseAndBrandContainingIgnoreCaseAndNameContainingIgnoreCase(
                    category != null ? category : "",
                    brand != null ? brand : "",
                    keyword != null ? keyword : "");
        } else {
            products = productRepo.findAll();
        }

        List<String> categories = productRepo.findAll()
                .stream().map(Product::getCategory).distinct().collect(Collectors.toList());
        List<String> brands = productRepo.findAll()
                .stream().map(Product::getBrand).distinct().collect(Collectors.toList());

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);

        return "product-listing";
    }

    @GetMapping("/products/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        Product product = productRepo.findById(id).orElse(null);

        if (product == null || product.getImage() == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Change if needed

        return new ResponseEntity<>(product.getImage(), headers, HttpStatus.OK);
    }
    //new code
    @GetMapping("/products/{id}")
    public String viewProductDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-details";
    }
    //
}




