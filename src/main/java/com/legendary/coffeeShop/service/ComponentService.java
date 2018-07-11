package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.ComponentForm;
import com.legendary.coffeeShop.dao.repositories.ComponentRepository;
import com.legendary.coffeeShop.utils.CommonConstants;
import com.legendary.coffeeShop.utils.Status;
import com.legendary.coffeeShop.dao.entities.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class ComponentService {


    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private CommonConstants commonConstants;


    /*********************************
     * Public Functions
     *********************************/

    public Set<Component> getComponentByType(String prodType) {
        return new HashSet<>(componentRepository.findByProductTypesContains(prodType));
    }

    public Status createComponent(ComponentForm componentForm) {
        Component component = prepareComponent(new Component(), componentForm);
        componentRepository.save(component);
        return new Status(Status.OK, "Component created successfully.");
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

    /*********************************
     * Private Functions
     *********************************/

    private Component prepareComponent(Component component, ComponentForm componentForm) {
        component.setName(componentForm.getName());
        component.setAmount(componentForm.getAmount());
        component.setPrice(componentForm.getPrice());
        component.setStatus(componentForm.getStatus());
        return component;
    }
    private Component getComponent(String componenName) {
        if(StringUtils.isEmpty(componenName)){
            return null;
        }
        return componentRepository.findByProductNameEquals(componenName.toLowerCase());
    }

}
