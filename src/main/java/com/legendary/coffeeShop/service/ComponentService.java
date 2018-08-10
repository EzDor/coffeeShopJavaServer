package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.ComponentForm;
import com.legendary.coffeeShop.dao.entities.Component;
import com.legendary.coffeeShop.dao.entities.ComponentStatus;
import com.legendary.coffeeShop.dao.entities.Product;
import com.legendary.coffeeShop.dao.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Create component by the given form
     */
    public void createComponent(ComponentForm componentForm) {
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

    /**
     * Update existing component with the given new data
     */
    public void updateComponent(ComponentForm componentForm) {
        Component component = getComponent(componentForm.getName());
        if (component == null) {
            throw new NoSuchElementException("Cannot update component, component with name " +
                    componentForm.getName() + " was not found");
        }
        component = prepareComponent(component, componentForm);
        componentRepository.save(component);

    }

    /**
     * Delete component with the given name
     */
    public void deleteComponent(String name) {
        Component component = getComponent(name);
        if (component == null) {
            throw new NoSuchElementException("Couldn't find component with name" + name);
        }
        component.getProductTypes().remove(this);
        componentRepository.delete(component);
    }


    /**
     * Get component by name
     */
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

    /**
     * Decrease component amount by 1
     */
    public void decreaseAmount(Component component) {
        int newAmount = component.getAmount()-1;
        if (newAmount < 0)
            throw new IllegalStateException("Component " + component.getName() + " is out of stock");

        if (newAmount == 0) {
            component.setStatus(ComponentStatus.OUT_OF_STOCK);
        }
        component.setAmount(newAmount-1);
        componentRepository.save(component);
    }
}
