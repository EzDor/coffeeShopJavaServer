package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComponentRepository extends JpaRepository<Component, Long> {
    List<Component> findByProductTypes_id(int id);
    Component findByNameEqualsIgnoreCase(String componentName);


}
