package com.legendary.coffeeShop.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;

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


    @Column
    private String name;

    @Column
    private String lastName;

    @NotBlank
    @Column(unique = true)
    private String username;

    @Column
    private UserSatus status;

    private String password;

    @Column(name = "creation_time")
    private Timestamp creationTime;

}
