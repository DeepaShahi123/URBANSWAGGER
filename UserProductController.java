package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/user") // ✅ User-facing routes start here
public class UserProductController {

    private final ProductService productService;

    public UserProductController(ProductService productService) {
        this.productService = productService;
    }

    // ✅ Serve product image by ID
    @GetMapping("/products/image/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null && product.getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(product.getImage(), headers, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ Show filtered product list or all products
    @GetMapping("/products")
    public String showProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            Model model) {

        List<Product> products;

        // Search/filter if any criteria provided
        if ((keyword != null && !keyword.isEmpty()) ||
            (category != null && !category.isEmpty()) ||
            (brand != null && !brand.isEmpty())) {
            products = productService.searchAndFilterProducts(keyword, category, brand);
        } else {
            products = productService.getAllProducts();
        }

        // Fetch filter options
        List<String> categories = productService.getAllCategories();
        List<String> brands = productService.getAllBrands();

        // Add attributes for Thymeleaf
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedBrand", brand);
        model.addAttribute("keyword", keyword);

        return "products"; // 📝 Make sure this is mapped correctly in templates
    }

    // ✅ Show detailed product view
    @GetMapping("/products/details/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/user/products";
        }

        model.addAttribute("product", product);
        return "product-detailss"; // 📝 Should match the template file name
    }
}
