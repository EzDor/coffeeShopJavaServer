package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.component.Component;
import com.legendary.coffeeShop.dao.entities.product.Product;
import com.legendary.coffeeShop.dao.entities.product.ProductStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findFirstByProductComponentsContains(Component component);
    Product findByType(String type);
    List<Product> findAllByStatus(ProductStatus status, Sort sort);
}
