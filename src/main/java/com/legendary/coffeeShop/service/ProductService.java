package com.legendary.coffeeShop.service;


import com.legendary.coffeeShop.controller.form.ProductForm;
import com.legendary.coffeeShop.dao.entities.Product;
import com.legendary.coffeeShop.dao.entities.ProductStatus;
import com.legendary.coffeeShop.dao.repositories.ProductRepository;
import com.legendary.coffeeShop.dao.repositories.OrderItemRepository;
import com.legendary.coffeeShop.utils.CommonConstants;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommonConstants commonConstants;


    /*********************************
     * Public Functions
     *********************************/

    public Set<Product> getProducts() {
        return new HashSet<>(productRepository.findAllByStatus(ProductStatus.ACTIVE));
    }

    public Status createProduct(ProductForm productForm) {

        if (getProduct(productForm.getProductType()) != null) {
            return new Status(Status.ERROR, "Cannot create product, productType " + productForm.getProductType() + " is already exist");
        }

        Product product = prepareProduct(new Product(), productForm);
        productRepository.save(product);
        return new Status(Status.OK, "Product is created successfully.");
    }

    public Status updateProduct(ProductForm productForm) {
        Product product = getProduct(productForm.getProductTypeToUpdate());
        if (product == null) {
            return new Status(Status.ERROR, "Cannot update product, product with productType " + productForm.getProductTypeToUpdate() + " is not found");
        }
        product = prepareProduct(product, productForm);
        productRepository.save(product);
        return new Status(Status.OK, "Product is updated successfully.");

    }

    /*********************************
     * Private Functions
     *********************************/

    private Product prepareProduct(Product product, ProductForm productForm) {
        product.setProductType(productForm.getProductType());
        product.setDisplayName(productForm.getDisplayName());
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        product.setStatus(ProductStatus.ACTIVE);
        return product;
    }


    private Product getProduct(String productType) {
        if(StringUtils.isEmpty(productType)){
            return null;
        }
        return productRepository.findByProductTypeAndStatus(productType.toLowerCase(), ProductStatus.ACTIVE);
    }

}
