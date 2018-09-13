package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.controller.form.NewComponentForm;
import com.legendary.coffeeShop.controller.form.UpdateComponentForm;
import com.legendary.coffeeShop.dao.entities.Component;
import com.legendary.coffeeShop.dao.entities.ComponentStatus;
import com.legendary.coffeeShop.dao.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ComponentService {


    private final ComponentRepository componentRepository;

    @Autowired
    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    /*********************************
     * Public Functions
     *********************************/

    /**
     * Create component by the given form
     */
    public void createComponent(NewComponentForm newComponentForm) {
        Component component = getComponent(newComponentForm.getType());
        if (component != null) {
            throw new IllegalArgumentException(String.format("Component with name %s already exists",
                    newComponentForm.getType()));
        }
        component = prepareComponent(new Component(), newComponentForm);
        componentRepository.save(component);
    }

    /**
     * Update existing component with the given new data
     */
    public void updateComponent(UpdateComponentForm updateComponentForm) {
        Component component = getComponent(updateComponentForm.getComponentTypeToUpdate());
        if (component == null) {
            throw new NoSuchElementException(String.format("Cannot update component, component with name %s " +
                    "was not found", updateComponentForm.getComponentTypeToUpdate()));
        }
        component = prepareComponent(component, updateComponentForm.getUpdatedComponentDetails());
        componentRepository.save(component);

    }

    /**
     * Delete component with the given type
     */
    public void deleteComponent(String type) {
        Component component = getComponent(type);
        if (component == null) {
            throw new NoSuchElementException(String.format("Couldn't find component with type %s", type));
        }
        componentRepository.delete(component);
    }


    /**
     * Get component by name
     */
    public Component getComponent(String componentType) {
        if (StringUtils.isEmpty(componentType)) {
            return null;
        }
        return componentRepository.findByType(componentType);
    }

    public List<Component> getComponents() {
        return componentRepository.findAll(sortByIdAsc());
    }

    /*********************************
     * Private Functions
     *********************************/

    private Component prepareComponent(Component component, NewComponentForm newComponentForm) {
        component.setName(newComponentForm.getName());
        component.setAmount(newComponentForm.getAmount());
        component.setPrice(newComponentForm.getPrice());
        component.setType(newComponentForm.getType());
        component.setImage(newComponentForm.getImage());
        component.setStatus(getComponentStatus(newComponentForm.getStatus()));

        return component;
    }

    private ComponentStatus getComponentStatus(String status) {
        switch (status) {
            case "ACTIVE":
                return ComponentStatus.ACTIVE;

            case "DISCARDED":
                return ComponentStatus.DISCARDED;

            default:
                return ComponentStatus.OUT_OF_STOCK;
        }
    }

    /**
     * Decrease component amount by 1
     */
    public void decreaseAmount(Component component) {
        int newAmount = component.getAmount() - 1;
        if (newAmount < 0)
            throw new IllegalStateException(String.format("Component %s is out of stock", component.getName()));

        if (newAmount == 0) {
            component.setStatus(ComponentStatus.OUT_OF_STOCK);
        }
        component.setAmount(newAmount - 1);
        componentRepository.save(component);
    }

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }
}
