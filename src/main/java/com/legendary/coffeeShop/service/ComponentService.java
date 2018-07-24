package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.ComponentForm;
import com.legendary.coffeeShop.dao.entities.Component;
import com.legendary.coffeeShop.dao.entities.ComponentStatus;
import com.legendary.coffeeShop.dao.entities.Product;
import com.legendary.coffeeShop.dao.repositories.ComponentRepository;
import com.legendary.coffeeShop.utils.CommonConstants;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ComponentService {


    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private CommonConstants commonConstants;
    @Autowired
    private ProductService productService;

    /*********************************
     * Public Functions
     *********************************/

    public Set<Component> getComponentByType(String prodType) {
        return new HashSet<>(componentRepository.findByProductTypesProductTypeContains(prodType));
    }

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
        // DELETE PRODUCT_TYPE
        component.getProductTypes().remove(this);
        componentRepository.delete(component);

        return new Status(Status.OK, "component deleted successfully.");
    }

    /*********************************
     * Private Functions
     *********************************/

    private Component prepareComponent(Component component, ComponentForm componentForm) {
        component.setName(componentForm.getName());
        component.setAmount(componentForm.getAmount());
        component.setPrice(componentForm.getPrice());
        component.setStatus(ComponentStatus.ACTIVE);
        List<Product> products = productService.getProductsByName(componentForm.getProductDisplayName());
        if (products != null) {
            component.getProductTypes().addAll(products);
            component.setProductTypes(component.getProductTypes());
            return component;
        }
        return null;

    }
    private Component getComponent(String componenName) {
        if(StringUtils.isEmpty(componenName)){
            return null;
        }
        return componentRepository.findByNameEqualsIgnoreCase(componenName);
    }

}
