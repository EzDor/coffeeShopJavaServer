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
import java.util.List;
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

    public List<Product> getProducts() {
        return productRepository.findAllByStatus(ProductStatus.ACTIVE);
    }

    public List<Product> getProductsByName(List<String> displayNames) {
        return productRepository.findByDisplayNameIn(displayNames);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id);
    }

    public Status createProduct(ProductForm productForm) {

        if (getProduct(productForm.getDisplayName()) != null) {
            return new Status(Status.ERROR, "Cannot create product, product with name " + productForm.getDisplayName() + " already exist");
        }

        Product product = prepareProduct(new Product(), productForm);
        productRepository.save(product);
        return new Status(Status.OK, "Product was created successfully.");
    }

    public Status updateProduct(ProductForm productForm) {
        Product product = getProduct(productForm.getDisplayName());
        if (product == null) {
            return new Status(Status.ERROR, "Cannot update product, product with name " + productForm.getDisplayName() + " is not found");
        }
        product = prepareProduct(product, productForm);
        productRepository.save(product);
        return new Status(Status.OK, "Product was updated successfully.");
    }

    public Status deleteProduct(String displayName) {
        Product product = getProduct(displayName);
        productRepository.delete(product);
        return new Status(Status.OK, "Product was deleted successfully.");
    }

    /*********************************
     * Private Functions
     *********************************/

    private Product prepareProduct(Product product, ProductForm productForm) {
        product.setProductType(productForm.getProductType());
        product.setDisplayName(productForm.getDisplayName());
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        String status = productForm.getProductStatus();
        if ( status == null)
            product.setStatus(ProductStatus.ACTIVE);
        else
            product.setStatus(ProductStatus.valueOf(status));
        return product;
    }


    private Product getProduct(String productDisplayName) {
        if(StringUtils.isEmpty(productDisplayName)){
            return null;
        }
        return productRepository.findByDisplayName(productDisplayName);
    }

}
