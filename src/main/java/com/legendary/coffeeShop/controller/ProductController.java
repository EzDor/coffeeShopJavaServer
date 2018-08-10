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
import java.util.NoSuchElementException;

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
        Product product = productService.getProduct(productName);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Product with name %s was not found", productName));
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity createProduct(@RequestBody ProductForm productForm) {
        try {
            validationService.validateProductForm(productForm);
            productService.createProduct(productForm);
            return ResponseEntity.status(HttpStatus.OK).body("Product was created successfully.");
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        } catch (IllegalArgumentException err){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(err.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity updateProduct(@RequestBody ProductForm productForm) {
        try {
            validationService.validateUpdateProductForm(productForm);
            productService.updateProduct(productForm);
            return ResponseEntity.status(HttpStatus.OK).body("Product was updated successfully.");
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        } catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(err.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{displayName}")
    @ResponseBody
    public ResponseEntity deleteProduct(@PathVariable String displayName) {
        try {
            productService.deleteProduct(displayName);
            return ResponseEntity.status(HttpStatus.OK).body("Product was deleted successfully.");
        }
        catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(err.getMessage());
        }
    }

    @GetMapping("/components/{productName}")
    @ResponseBody
    public ResponseEntity getProductComponents(@PathVariable String productName) {
        try {
            List<Component> components = productService.getProductComponents(productName);
            return ResponseEntity.status(HttpStatus.OK).body(components);
        }
        catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
        }
    }
}
