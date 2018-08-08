package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.controller.form.ProductForm;
import com.legendary.coffeeShop.dao.entities.Product;
import com.legendary.coffeeShop.service.ProductService;
import com.legendary.coffeeShop.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ValidationService validationService;

    @GetMapping
    @ResponseBody
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/type/{productType}")
    @ResponseBody
    public ResponseEntity getProductsByType(@PathVariable String productType) {
        try {
            validationService.validateProductType(productType);
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProductsByType(productType));
    }

    @GetMapping("/name/{productName}")
    @ResponseBody
    public ResponseEntity getProductsByName(@PathVariable String productName) {
        Product p = productService.getProduct(productName);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with name " + productName + "was not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity createProduct(@RequestBody ProductForm productForm) {
        try {
            validationService.validateProductForm(productForm);
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
        return productService.createProduct(productForm);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity updateProduct(@RequestBody ProductForm productForm) {
        try {
            validationService.validateUpdateProductForm(productForm);
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
        return productService.updateProduct(productForm);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{displayName}")
    @ResponseBody
    public ResponseEntity deleteProduct(@PathVariable String displayName) {
        return productService.deleteProduct(displayName);
    }

    @GetMapping("/components/{productName}")
    @ResponseBody
    public ResponseEntity getProductComponents(@PathVariable String productName) {
        return productService.getProductComponents(productName);
    }
}
