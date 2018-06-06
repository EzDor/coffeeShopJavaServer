package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.dao.entities.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/details")
    @ResponseBody
    public Set<ProductDetails> getProductsDetails(){

        return productService.getProductsDetails();
    }
}
