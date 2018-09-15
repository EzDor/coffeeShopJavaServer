package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.controller.form.ProductForm;
import com.legendary.coffeeShop.controller.form.UpdatedProductForm;
import com.legendary.coffeeShop.dao.entities.product.Product;
import com.legendary.coffeeShop.service.ProductService;
import com.legendary.coffeeShop.service.ValidationService;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final ValidationService validationService;

    @Autowired
    public ProductController(ProductService productService, ValidationService validationService) {
        this.productService = productService;
        this.validationService = validationService;
    }

    @GetMapping("active")
    @ResponseBody
    public List<Product> getActiveProducts() {
        return productService.getActiveProducts();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseBody
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("type")
    @ResponseBody
    public Product getProduct(@RequestParam String productType) {
        return productService.getProduct(productType);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @ResponseBody
    public Status createProduct(@RequestBody ProductForm productForm) {
        validationService.validateProductForm(productForm);
        productService.createProduct(productForm);
        return new Status("Product was created successfully.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    @ResponseBody
    public Status updateProduct(@RequestBody UpdatedProductForm updatedProductForm) {

        validationService.validateProductForm(updatedProductForm.getUpdatedProductDetails());
        productService.updateProduct(updatedProductForm);
        return new Status("Product was updated successfully.");

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity deleteProduct(@RequestParam String productType) {
        try {
            productService.deleteProduct(productType);
            return ResponseEntity.status(HttpStatus.OK).body("Product was deleted successfully.");
        } catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(err.getMessage());
        }
    }

}
