package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.Component;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ComponentRepository extends JpaRepository<Component, Long> {
    Component findByType(String componentType);
}
