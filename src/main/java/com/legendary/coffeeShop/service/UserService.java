package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.UserForm;
import com.legendary.coffeeShop.dao.entities.User;
import com.legendary.coffeeShop.dao.entities.UserSatus;
import com.legendary.coffeeShop.dao.repositories.UserRepository;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameAndStatus(username, UserSatus.ACTIVE);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), emptyList());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

//    public void delete(long id) {
//        userRepository.delete(id);
//    }

    public Status createUser(UserForm userForm) {
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setStatus(UserSatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        userRepository.save(user);
        return new Status(Status.OK);
    }
}
