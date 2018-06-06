package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduceDetailsRepository extends JpaRepository<ProductDetails, Long> {
}
