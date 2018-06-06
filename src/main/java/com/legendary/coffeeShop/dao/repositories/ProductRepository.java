package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
