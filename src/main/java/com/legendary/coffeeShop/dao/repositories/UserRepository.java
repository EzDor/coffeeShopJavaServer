package com.legendary.coffeeShop.dao.repositories;

import com.legendary.coffeeShop.dao.entities.User;
import com.legendary.coffeeShop.dao.entities.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndStatus(String username, UserStatus userStatus);
    List<User> findAllByStatusOrderById(UserStatus userStatus);

}
