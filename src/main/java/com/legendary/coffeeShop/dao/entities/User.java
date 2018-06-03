package com.legendary.coffeeShop.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(unique = true)
    private String username;

    @Column
    private UserStatus status;

    @NotBlank
    @Column
    private String password;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @Column(name = "is_admin")
    private boolean isAdmin;
}
