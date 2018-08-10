package com.legendary.coffeeShop.service;


import com.legendary.coffeeShop.controller.form.ProductForm;
import com.legendary.coffeeShop.dao.entities.Component;
import com.legendary.coffeeShop.dao.entities.Product;
import com.legendary.coffeeShop.dao.entities.ProductStatus;
import com.legendary.coffeeShop.dao.entities.ProductType;
import com.legendary.coffeeShop.dao.repositories.ComponentRepository;
import com.legendary.coffeeShop.dao.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ComponentRepository componentRepository;


    /*********************************
     * Public Functions
     *********************************/


    /**
     * Get product by name
     */
    public Product getProduct(String productName) {
        return productRepository.findByDisplayName(productName);
    }

    /**
     * Get all products
     */
    public List<Product> getProducts() {
        return productRepository.findByStatusEquals(ProductStatus.ACTIVE);
    }

    /**
     * Get list of products by given list of names
     */
    public List<Product> getProductsByNames(List<String> displayNames) {
        return productRepository.findByDisplayNameIn(displayNames);
    }

    /**
     * Get all products of specific type
     */
    public List<Product> getProductsByType(String productType) {
        return productRepository.findByProductType(ProductType.valueOf(productType.toUpperCase()));
    }

    /**
     * Create new product by the given form
     */
    public void createProduct(ProductForm productForm) {
        if (getProduct(productForm.getDisplayName()) != null) {
            throw new IllegalArgumentException(String .format("Cannot create product, product with name %s " +
                    "already exist", productForm.getDisplayName()));
        }

        Product product = prepareProduct(new Product(), productForm);
        productRepository.save(product);
    }

    /**
     * Update product with new data
     */
    public void updateProduct(ProductForm productForm) {
        Product product = getProduct(productForm.getDisplayName());
        if (product == null) {
            throw new NoSuchElementException(String.format("Cannot update product, product with name %s " +
                    "was not found", productForm.getDisplayName()));
        }
        product = prepareProduct(product, productForm);
        productRepository.save(product);
    }

    /**
     * Delete product with the given name
     */
    public void deleteProduct(String displayName) {
        Product product = getProduct(displayName);
        if (product == null) {
            throw new NoSuchElementException(String.format("Cannot delete product, product with name %s " +
                    "was not found", displayName));
        }
        productRepository.delete(product);
    }

    /**
     * Get components list of the given product name
     */
    public List<Component> getProductComponents(String productName) {
        Product product = getProduct(productName);
        if (product == null) {
           throw new NoSuchElementException(String.format("Product %s not found.", productName));
        }
        return componentRepository.findByProductTypes_id(product.getId());
    }

    /*********************************
     * Private Functions
     *********************************/

    /**
     * Update product with the given data. If some data doesnt exist in form it won't update this field
     */
    private Product prepareProduct(Product product, ProductForm productForm) {
        product.setProductType(ProductType.valueOf(productForm.getProductType().toUpperCase()));
        product.setDisplayName(productForm.getDisplayName());
        String description = productForm.getDescription();
        if (description == null) {
            description = product.getDescription();
        }
        product.setDescription(description);
        double price = productForm.getPrice();
        if (price == 0.0) {
            price = product.getPrice();
        }
        product.setPrice(price);
        String status = productForm.getProductStatus();
        if (status == null) {
            ProductStatus productStatus = product.getStatus();
            if (productStatus == null)
                product.setStatus(ProductStatus.ACTIVE);
            else
                product.setStatus(productStatus);
        }
        else
            product.setStatus(ProductStatus.valueOf(status.toUpperCase()));
        return product;
    }

}
