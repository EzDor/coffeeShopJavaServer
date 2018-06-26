package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.Component;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ComponentRepository extends JpaRepository<Component, Long> {
    Set<Component> findByAmountEquals(String componentType);
}
