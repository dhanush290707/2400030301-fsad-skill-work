package com.klu.repository;

import com.klu.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Derived query methods
    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(double min, double max);

    // JPQL queries using @Query
    // a. Sorting products by price
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllSortedByPrice();

    // b. Fetching products above a price value
    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findProductsAbovePrice(@Param("price") double price);

    // c. Fetching products by category
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findProductsByCategoryUsingJPQL(@Param("category") String category);
}
