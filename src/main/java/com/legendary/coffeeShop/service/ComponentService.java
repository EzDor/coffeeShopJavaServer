package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.component.ComponentForm;
import com.legendary.coffeeShop.controller.form.component.UpdatedComponentForm;
import com.legendary.coffeeShop.dao.entities.component.Component;
import com.legendary.coffeeShop.dao.entities.component.ComponentStatus;
import com.legendary.coffeeShop.dao.repositories.ComponentRepository;
import com.legendary.coffeeShop.utils.CommonConstants;
import com.legendary.coffeeShop.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


@Service
public class ComponentService {

    private final CommonConstants commonConstants;
    private final ComponentRepository componentRepository;

    @Autowired
    public ComponentService(
            CommonConstants commonConstants,
            ComponentRepository componentRepository) {

        this.commonConstants = commonConstants;
        this.componentRepository = componentRepository;
    }

    /*********************************
     * Public Functions
     *********************************/

    public void createComponent(ComponentForm componentForm) {
        Component component = getComponent(componentForm.getType());
        if (component != null) {
            throw new IllegalArgumentException(String.format("Component with type %s is already exists",
                    componentForm.getType()));
        }
        component = prepareComponent(new Component(), componentForm);
        componentRepository.save(component);
    }


    public void updateComponent(UpdatedComponentForm updatedComponentForm) {
        Component component = getComponent(updatedComponentForm.getComponentTypeToUpdate());
        if (component == null) {
            throw new NoSuchElementException(String.format("Cannot update component, component with name %s " +
                    "was not found", updatedComponentForm.getComponentTypeToUpdate()));
        }
        component = prepareComponent(component, updatedComponentForm.getUpdatedComponentDetails());
        componentRepository.save(component);

    }


    public void deleteComponent(String type) {
        Component component = getComponent(type);
        if (component == null) {
            throw new NoSuchElementException(String.format("Couldn't find component with type %s", type));
        }
        component.setStatus(ComponentStatus.DISCARDED);
        componentRepository.save(component);
    }


    public List<Component> getComponents() {
        return componentRepository.findAll(CommonUtils.sortAscBy(commonConstants.getComponentSortKey()));
    }


    Set<Component> getComponentsByType(List<String> componentTypes) {
        return new HashSet<>(componentRepository.findAllByTypeInAndStatus(componentTypes, ComponentStatus.ACTIVE));
    }

    void decreaseAmount(Set<Component> components) {
        for (Component component : components) {
            decreaseAmount(component);
        }
        componentRepository.saveAll(components);
    }

    /*********************************
     * Private Functions
     *********************************/

    private Component getComponent(String componentType) {
        if (StringUtils.isEmpty(componentType)) {
            return null;
        }
        return componentRepository.findByType(componentType.toLowerCase());
    }

    private Component prepareComponent(Component component, ComponentForm componentForm) {
        component.setName(componentForm.getName());
        component.setAmount(componentForm.getAmount());
        component.setPrice(componentForm.getPrice());
        component.setType(componentForm.getType().toLowerCase());
        component.setImage(componentForm.getImage());
        component.setStatus(componentForm.getStatus());

        return component;
    }


    private void decreaseAmount(Component component) {

        int newAmount = component.getAmount() - 1;
        if (newAmount < 0) {
            throw new IllegalStateException(String.format("Component %s is out of stock", component.getType()));
        }
        else if (newAmount == 0) {
            component.setStatus(ComponentStatus.OUT_OF_STOCK);
        }

        component.setAmount(newAmount);
    }
}
