package com.legendary.coffeeShop.service;


import com.legendary.coffeeShop.controller.form.ProductForm;
import com.legendary.coffeeShop.controller.form.UpdatedProductForm;
import com.legendary.coffeeShop.dao.entities.component.Component;
import com.legendary.coffeeShop.dao.entities.product.Product;
import com.legendary.coffeeShop.dao.entities.product.ProductStatus;
import com.legendary.coffeeShop.dao.repositories.ComponentRepository;
import com.legendary.coffeeShop.dao.repositories.ProductRepository;
import com.legendary.coffeeShop.utils.CommonConstants;
import com.legendary.coffeeShop.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CommonConstants commonConstants;
    private final ComponentRepository componentRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CommonConstants commonConstants, ComponentRepository componentRepository) {
        this.productRepository = productRepository;
        this.commonConstants = commonConstants;
        this.componentRepository = componentRepository;
    }


    /*********************************
     * Public Functions
     *********************************/

    public Product getProduct(String productType) {
        return productRepository.findByType(productType);
    }

    public List<Product> getActiveProducts() {
        return productRepository.findAllByStatus(ProductStatus.ACTIVE, CommonUtils.sortAscBy(commonConstants.getProductSortKey()));
    }

    public List<Product> getProducts() {
        return productRepository.findAll(CommonUtils.sortAscBy(commonConstants.getProductSortKey()));
    }

    public void createProduct(ProductForm productForm) {
        if (getProduct(productForm.getType()) != null) {
            throw new IllegalArgumentException(String.format("Cannot create product, product with type %s " +
                    "already exist", productForm.getType()));
        }

        Product product = prepareProduct(new Product(), productForm);
        productRepository.save(product);
    }

    /**
     * Update product with new data
     */
    public void updateProduct(UpdatedProductForm updatedProductForm) {
        Product product = getProduct(updatedProductForm.getProductTypeToUpdate());
        if (product == null) {
            throw new NoSuchElementException(String.format("Cannot update product, product with type %s " +
                    "was not found", updatedProductForm.getProductTypeToUpdate()));
        }
        product = prepareProduct(product, updatedProductForm.getUpdatedProductDetails());
        productRepository.save(product);
    }

    /**
     * Delete product with the given name
     */
    public void deleteProduct(String type) {
        Product product = getProduct(type);
        if (product == null) {
            throw new NoSuchElementException(String.format("Cannot delete product, product with type %s " +
                    "was not found", type));
        }
        product.setStatus(ProductStatus.DISCARDED);
        productRepository.save(product);
    }


    /*********************************
     * Private Functions
     *********************************/

    private Product prepareProduct(Product product, ProductForm productForm) {

        product.setType(productForm.getType().toLowerCase());
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        product.setStatus(productForm.getStatus());
        product.setImage(productForm.getImage());
        product.setProductComponents(getComponentsByType(productForm.getComponentsTypes()));

        return product;
    }

    private Set<Component> getComponentsByType(List<String> componentTypes) {
        Set<Component> components = new HashSet<>(componentRepository.findAllByTypeIn(componentTypes));
        if (componentTypes.size() != components.size()) {
            throw new IllegalArgumentException("Cannot update product, some components is not exist.");
        }

        return components;
    }

}
