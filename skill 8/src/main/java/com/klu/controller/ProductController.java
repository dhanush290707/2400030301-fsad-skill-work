package com.klu.controller;

import com.klu.model.Product;
import com.klu.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/filter")
    public List<Product> getProductsByPriceRange(
            @RequestParam("min") double min,
            @RequestParam("max") double max) {
        return productService.getProductsByPriceRange(min, max);
    }

    @GetMapping("/sorted")
    public List<Product> getProductsSortedByPrice() {
        return productService.getProductsSortedByPrice();
    }

    @GetMapping("/expensive/{price}")
    public List<Product> getExpensiveProducts(@PathVariable("price") double price) {
        return productService.getExpensiveProducts(price);
    }
}
