package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.UserForm;
import com.legendary.coffeeShop.controller.form.UpdatedUserForm;
import com.legendary.coffeeShop.dao.entities.User;
import com.legendary.coffeeShop.dao.entities.UserStatus;
import com.legendary.coffeeShop.dao.repositories.UserRepository;
import com.legendary.coffeeShop.utils.CommonConstants;
import com.legendary.coffeeShop.utils.CommonUtils;
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
import java.util.NoSuchElementException;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final CommonConstants commonConstants;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, CommonConstants commonConstants, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.commonConstants = commonConstants;
        this.passwordEncoder = passwordEncoder;
    }

    /*********************************
     * Public Functions
     *********************************/

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getActiveUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(getUserPermission(user));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorityList);
    }


    public Status createUser(UserForm userForm, boolean isAdminRequest) {
        isUserNameExists(userForm);
        User user = new User();
        user = prepareUser(user, userForm, isAdminRequest, true);
        userRepository.save(user);
        return new Status("User created successfully");
    }


    public void updateUser(UpdatedUserForm updatedUserForm, boolean isAdminRequest) {

        User user = getUserToUpdate(updatedUserForm.getUsernameToUpdate(), isAdminRequest);
        isUserExists(updatedUserForm.getUsernameToUpdate(), user);
        passwordValidation(updatedUserForm, isAdminRequest, user);
        user = prepareUser(user, updatedUserForm.getUpdatedUserDetails(), isAdminRequest, !isAdminRequest);
        userRepository.save(user);
    }


    public List<User> getUsers() {
        return userRepository.findAll(CommonUtils.sortAscBy(commonConstants.getUserSortKey()));
    }


    public Status deleteUser(String username) {
        User user = getUser(username);
        isUserExists(username, user);
        user.setStatus(UserStatus.DISCARDED);
        userRepository.save(user);
        return new Status("User is discarded successfully");
    }


    /*********************************
     * Private Functions
     *********************************/

    /**
     * Update User object by the given form
     */
    private User prepareUser(User user, UserForm userForm, boolean isAdminRequest, boolean updatePassword) {
        user.setUsername(userForm.getUsername().toLowerCase());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setStatus(userForm.getStatus());
        user.setAdmin(userForm.isAdmin() && isAdminRequest);

        if (updatePassword) {
            user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        }

        return user;
    }

    private String getUserPermission(User user) {
        return user.isAdmin() ? commonConstants.getAdminPermission() : commonConstants.getUserPermission();
    }

    private User getActiveUser(String username) {
        return userRepository.findByUsernameAndStatus(username.toLowerCase(), UserStatus.ACTIVE);
    }

    private User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    private User getUserToUpdate(String username, boolean isAdminRequest) {
        return isAdminRequest ? getUser(username) : getActiveUser(username);
    }

    private void isUserExists(String username, User user) {
        if (user == null) {
            throw new NoSuchElementException(String.format("Cannot update user %s. User Not Found",
                    username));
        }
    }

    private void passwordValidation(UpdatedUserForm updatedUserForm, boolean isAdminRequest, User user) {
        if (!isAdminRequest && !passwordEncoder.matches(updatedUserForm.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException(String.format("Cannot update user %s. Wrong username or password",
                    updatedUserForm.getUsernameToUpdate()));
        }
    }

    private void isUserNameExists(UserForm userForm) {
        if (getActiveUser(userForm.getUsername()) != null) {
            throw new IllegalArgumentException("Cannot create user, username " + userForm.getUsername() + " already exist");
        }
    }
}
