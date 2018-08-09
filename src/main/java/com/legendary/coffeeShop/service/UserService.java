package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.NewUserForm;
import com.legendary.coffeeShop.controller.form.UpdateUserForm;
import com.legendary.coffeeShop.dao.entities.User;
import com.legendary.coffeeShop.dao.entities.UserStatus;
import com.legendary.coffeeShop.dao.repositories.UserRepository;
import com.legendary.coffeeShop.utils.CommonConstants;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

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
        User user = getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(getUserPermission(user));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorityList);
    }


    /**
     * Create new user from the given form
     * @throws NoSuchElementException if no product found with the given name
     */
    public void createUser(NewUserForm userForm) throws NoSuchElementException {
        if (getUser(userForm.getUsername()) != null) {
            throw new IllegalArgumentException("Cannot create user, username " + userForm.getUsername() + " already exist");
        }
        User user = new User();
        user = prepareUser(user, userForm);
        userRepository.save(user);

    }


    /**
     * Update given user according to user form
     * @throws NoSuchElementException if no product found with the given name
     * @throws IllegalAccessException if username or password is wrong
     */
    public void updateUser(UpdateUserForm userForm) throws NoSuchElementException, IllegalAccessException {
        User user = getUser(userForm.getUsernameToUpdate());
        if (user == null) {
            throw new NoSuchElementException("Cannot update user " + userForm.getUsernameToUpdate()
                    + ". User Not Found");
        }
        if (!passwordEncoder.matches(userForm.getPassword(), user.getPassword())) {
            throw new IllegalAccessException("Cannot update user " + userForm.getUsernameToUpdate()
                    + ". Wrong username or password");
        }
        user = prepareUser(user, userForm.getUpdatedUserDetails());
        userRepository.save(user);
    }


    /**
     * Get user by given name
     */
    public User getUser(String username) {
        return userRepository.findByUsernameAndStatus(username.toLowerCase(), UserStatus.ACTIVE);
    }

    /*********************************
     * Private Functions
     *********************************/

    /**
     * Update User object by the given form
     */
    private User prepareUser(User user, NewUserForm userForm) {
        user.setUsername(userForm.getUsername().toLowerCase());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setStatus(UserStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setCreationTime(new Timestamp(System.currentTimeMillis()));
        user.setAdmin(false);

        return user;
    }

    /**
     * Check user permission
     */
    private String getUserPermission(User user) {
        return user.isAdmin() ? commonConstants.getAdminPermission() : commonConstants.getUserPermission();
    }

}
