package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
