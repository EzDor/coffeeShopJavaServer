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
import java.util.NoSuchElementException;


@Service
public class ComponentService {


    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ProductService productService;

    /*********************************
     * Public Functions
     *********************************/

    public void createComponent(ComponentForm componentForm) throws IllegalArgumentException {
        Component component = getComponent(componentForm.getName());
        if (component != null ) {
            throw new IllegalArgumentException("Component with this name already exists");
        }
        component = prepareComponent(new Component(), componentForm);
        if (component != null) {
            componentRepository.save(component);
        }
        else {
            throw new IllegalArgumentException("Component was not created successfully");
        }
    }

    public void updateComponent(ComponentForm componentForm) throws NoSuchElementException {
        Component component = getComponent(componentForm.getName());
        if (component == null) {
            throw new NoSuchElementException("Cannot update component, component with name " +
                    componentForm.getName() + " was not found");
        }
        component = prepareComponent(component, componentForm);
        componentRepository.save(component);

    }

    public void deleteComponent(String name) throws NoSuchElementException{
        Component component = getComponent(name);
        if (component == null) {
            throw new NoSuchElementException("Couldn't find component with name" + name);
        }
        component.getProductTypes().remove(this);
        componentRepository.delete(component);
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
            // TODO: not working with list of products
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
