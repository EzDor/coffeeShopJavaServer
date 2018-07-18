package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.Product;
import com.legendary.coffeeShop.dao.entities.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductTypeAndStatus(String productType, ProductStatus status);

    Set<Product> findAllByStatus(ProductStatus status);

    Product findById(int id);
}
