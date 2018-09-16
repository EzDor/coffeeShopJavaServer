package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.user.User;
import com.legendary.coffeeShop.dao.entities.user.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndStatus(String username, UserStatus userStatus);
    User findByUsername(String username);

}
