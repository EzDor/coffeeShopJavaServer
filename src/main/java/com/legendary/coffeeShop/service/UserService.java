package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.UserForm;
import com.legendary.coffeeShop.dao.entities.User;
import com.legendary.coffeeShop.dao.entities.UserStatus;
import com.legendary.coffeeShop.dao.repositories.UserRepository;
import com.legendary.coffeeShop.utils.CommonConstants;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonConstants commonConstants;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /*********************************
     * Public Functions
     *********************************/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameAndStatus(username.toLowerCase(), UserStatus.ACTIVE);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(getUserPermission(user));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorityList);
    }


    public Status createUser(UserForm userForm) {
        if (getUser(userForm.getUsername()) != null) {
            return new Status(Status.ERROR, "Cannot create user, username " + userForm.getUsername() + " is already exist");
        }

        User user = new User();
        user = prepareUser(user, userForm);
        userRepository.save(user);
        return new Status(Status.OK);

    }


    public Status updateUser(UserForm userForm) {
        User user = getUser(userForm.getUsernameToUpdate());
        if (user == null) {
            return new Status(Status.ERROR, "Cannot update user, user with username " + userForm.getUsernameToUpdate() + " is not found");
        }

        user = prepareUser(user, userForm);
        userRepository.save(user);
        return new Status(Status.OK, "user is updated successfully.");

    }


    /*********************************
     * Private Functions
     *********************************/

    private User prepareUser(User user, UserForm userForm) {
        user.setUsername(userForm.getUsername().toLowerCase());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setStatus(UserStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setAdmin(false);

        return user;
    }

    private String getUserPermission(User user) {
        return user.isAdmin() ? commonConstants.getAdminPermission() : commonConstants.getUserPermission();
    }

    private User getUser(String username) {
        return userRepository.findByUsernameAndStatus(username.toLowerCase(), UserStatus.ACTIVE);
    }

}
