package com.klu.service;

import com.klu.model.Product;
import com.klu.repository.ProductRepository;
import com.klu.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found in category: " + category);
        }
        return products;
    }

    public List<Product> getProductsByPriceRange(double min, double max) {
        List<Product> products = productRepository.findByPriceBetween(min, max);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found in the specified price range.");
        }
        return products;
    }

    public List<Product> getProductsSortedByPrice() {
        return productRepository.findAllSortedByPrice();
    }

    public List<Product> getExpensiveProducts(double price) {
        List<Product> products = productRepository.findProductsAbovePrice(price);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found above price: " + price);
        }
        return products;
    }
}
