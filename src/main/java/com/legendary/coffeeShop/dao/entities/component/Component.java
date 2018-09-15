package com.legendary.coffeeShop.dao.entities.component;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "components")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String type;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private ComponentStatus status;

    @Column
    private int amount;

    @Column
    private String image;
    

}
