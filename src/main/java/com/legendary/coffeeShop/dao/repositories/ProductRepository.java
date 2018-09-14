package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.Component;
import com.legendary.coffeeShop.dao.entities.Product;
import com.legendary.coffeeShop.dao.entities.ProductStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findFirstByProductComponentsContains(Component component);
    Product findByProductType(String productType);
    List<Product> findAllByStatus(ProductStatus status, Sort sort);
}
