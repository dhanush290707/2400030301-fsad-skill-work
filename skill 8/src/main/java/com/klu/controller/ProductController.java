package com.klu.controller;

import com.klu.model.Product;
import com.klu.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category) {
        return productRepository.findByCategory(category);
    }

    @GetMapping("/filter")
    public List<Product> getProductsByPriceRange(
            @RequestParam("min") double min,
            @RequestParam("max") double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    @GetMapping("/sorted")
    public List<Product> getProductsSortedByPrice() {
        return productRepository.findAllSortedByPrice();
    }

    @GetMapping("/expensive/{price}")
    public List<Product> getExpensiveProducts(@PathVariable("price") double price) {
        return productRepository.findProductsAbovePrice(price);
    }
}
