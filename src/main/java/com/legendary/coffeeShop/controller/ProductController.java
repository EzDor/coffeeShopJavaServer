package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.controller.form.ProductForm;
import com.legendary.coffeeShop.dao.entities.Component;
import com.legendary.coffeeShop.dao.entities.Product;
import com.legendary.coffeeShop.service.ProductService;
import com.legendary.coffeeShop.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{productType}")
    @ResponseBody
    public List<Product> getProductsByType(@PathVariable String productType) {
        return productService.getProductsByType(productType);
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
            validationService.validateProductForm(productForm);
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
    public List<Component> getComponentByType(@PathVariable String productName) {
        return productService.getProductComponents(productName);
    }
}
