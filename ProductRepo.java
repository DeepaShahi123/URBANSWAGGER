package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
    
   

	    /*List<Product> findByNameContainingIgnoreCase(String keyword);

	    List<Product> findByCategory(String category);

	    List<Product> findByBrand(String brand);

	    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
	    
	    @Query("SELECT DISTINCT p.category FROM Product p")
	    List<String> findDistinctCategories();

	    @Query("SELECT DISTINCT p.brand FROM Product p")
	    List<String> findDistinctBrands();

	}

*/
	
	List<Product> findByCategoryContainingIgnoreCaseAndBrandContainingIgnoreCaseAndNameContainingIgnoreCase(
            String category, String brand, String name);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findDistinctCategories();

    @Query("SELECT DISTINCT p.brand FROM Product p")
    List<String> findDistinctBrands();
}
	
