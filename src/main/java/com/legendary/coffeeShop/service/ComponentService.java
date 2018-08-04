package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.ComponentForm;
import com.legendary.coffeeShop.dao.entities.Component;
import com.legendary.coffeeShop.dao.entities.ComponentStatus;
import com.legendary.coffeeShop.dao.entities.Product;
import com.legendary.coffeeShop.dao.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;


@Service
public class ComponentService {


    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ProductService productService;

    /*********************************
     * Public Functions
     *********************************/

    public ResponseEntity createComponent(ComponentForm componentForm) {
        Component component = getComponent(componentForm.getName());
        if (component != null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Component with this name already exists");
        }
        component = prepareComponent(new Component(), componentForm);
        if (component != null) {
            componentRepository.save(component);
            return ResponseEntity.status(HttpStatus.OK).body("Component created successfully.");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Component was not created successfully");
        }
    }

    public ResponseEntity updateComponent(ComponentForm componentForm) {
        Component component = getComponent(componentForm.getName());
        if (component == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cannot update component, component with name " +
                    componentForm.getName() + " was not found");
        }
        component = prepareComponent(component, componentForm);
        componentRepository.save(component);
        return ResponseEntity.status(HttpStatus.OK)
                .body("component updated successfully.");

    }

    public ResponseEntity deleteComponent(String name) {
        Component component = getComponent(name);
        if (component == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Couldn't find component with name" + name);
        }
        component.getProductTypes().remove(this);
        componentRepository.delete(component);

        return ResponseEntity.status(HttpStatus.OK)
                .body("component deleted successfully.");
    }


    public Component getComponent(String componentName) {
        if(StringUtils.isEmpty(componentName)){
            return null;
        }
        return componentRepository.findByNameEqualsIgnoreCase(componentName);
    }

    /*********************************
     * Private Functions
     *********************************/

    private Component prepareComponent(Component component, ComponentForm componentForm) {
        double price = componentForm.getPrice();
        component.setName(componentForm.getName());
        component.setAmount(componentForm.getAmount());
        component.setPrice(price);
        String status = componentForm.getComponentStatus();
        component.setStatus(getComponentStatus(status, price));

        List<Product> products = productService.getProductsByNames(componentForm.getProductDisplayName());
        if (products != null) {
            List<Product> currentProducts = component.getProductTypes();
            if (currentProducts != null)
                products.addAll(component.getProductTypes());
            component.setProductTypes(products);
            return component;
        }
        return null;

    }

    private ComponentStatus getComponentStatus(String status, double price) {
        if (price ==0){
            if (status == null || ComponentStatus.valueOf(status) == ComponentStatus.ACTIVE)
                return ComponentStatus.OUT_OF_STOCK;
            else
                return ComponentStatus.valueOf(status);
        }
        else if (status != null)
                return ComponentStatus.valueOf(status);
        else
            return ComponentStatus.ACTIVE;
    }

}
