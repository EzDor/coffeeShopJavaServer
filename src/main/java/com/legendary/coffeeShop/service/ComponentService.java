package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.ComponentForm;
import com.legendary.coffeeShop.dao.entities.Component;
import com.legendary.coffeeShop.dao.entities.ComponentStatus;
import com.legendary.coffeeShop.dao.entities.Product;
import com.legendary.coffeeShop.dao.repositories.ComponentRepository;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Status createComponent(ComponentForm componentForm) {
        Component component = getComponent(componentForm.getName());
        if (component != null ) {
            return new Status(Status.ERROR, "Component with this name already exists");
        }
        component = prepareComponent(new Component(), componentForm);
        if (component != null) {
            componentRepository.save(component);
            return new Status(Status.OK, "Component created successfully.");
        }
        else {
            return new Status(Status.ERROR, "Component was not created successfully");
        }
    }

    public Status updateComponent(ComponentForm componentForm) {
        Component component = getComponent(componentForm.getName());
        if (component == null) {
            return new Status(Status.ERROR, "Cannot update component, component with name " +
                    componentForm.getName() + " is not found");
        }
        component = prepareComponent(component, componentForm);
        componentRepository.save(component);
        return new Status(Status.OK, "component updated successfully.");

    }

    public Status deleteComponent(String name) {
        Component component = getComponent(name);
        if (component == null) {
            return new Status(Status.ERROR, "Couldn't find component with name" + name);
        }
        component.getProductTypes().remove(this);
        componentRepository.delete(component);

        return new Status(Status.OK, "component deleted successfully.");
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
