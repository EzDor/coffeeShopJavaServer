package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.Product;
import com.legendary.coffeeShop.dao.entities.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByDisplayName(String displayName);
    List<Product> findByDisplayNameIn(List<String> displayNames);
    List<Product> findByStatusEquals(ProductStatus status);
    Product findById(int id);
}
