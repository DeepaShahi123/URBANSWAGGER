package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepo;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    // ✅ For Admin CRUD
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }

    // ✅ For UI: Product listing
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    // ✅ For Search and Filters
    public List<Product> searchAndFilterProducts(String keyword, String category, String brand) {
        return productRepo.findAll().stream()
                .filter(p -> keyword == null || keyword.isEmpty() || p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .filter(p -> category == null || category.isEmpty() || p.getCategory().equalsIgnoreCase(category))
                .filter(p -> brand == null || brand.isEmpty() || p.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    public List<Product> filterByPriceRange(Double minPrice, Double maxPrice) {
        return productRepo.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> filterProducts(String category, String brand, Double minPrice, Double maxPrice) {
        return productRepo.findAll().stream()
                .filter(p -> category == null || category.isEmpty() || p.getCategory().equalsIgnoreCase(category))
                .filter(p -> brand == null || brand.isEmpty() || p.getBrand().equalsIgnoreCase(brand))
                .filter(p -> (minPrice == null || p.getPrice() >= minPrice) &&
                             (maxPrice == null || p.getPrice() <= maxPrice))
                .collect(Collectors.toList());
    }

    // ✅ For UI dropdowns
    public List<String> getAllCategories() {
        return productRepo.findDistinctCategories();
    }

    public List<String> getAllBrands() {
        return productRepo.findDistinctBrands();
    }
    public Product saveProduct(Product product) {
        return productRepo.save(product);
   
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    public Page<Product> getAllProductsPaginated(Pageable pageable) {
        return productRepo.findAll(pageable);
    }
}






