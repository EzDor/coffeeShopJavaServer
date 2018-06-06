package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.dao.entities.ProductDetails;
import com.legendary.coffeeShop.dao.repositories.ProduceDetailsRepository;
import com.legendary.coffeeShop.dao.repositories.ProductRepository;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProduceDetailsRepository produceDetailsRepository;


    public Set<ProductDetails> getProductsDetails() {
        return new HashSet<>(produceDetailsRepository.findAll());
    }

    public Status createProductDetails() {
        return new Status(Status.OK);
    }

}
