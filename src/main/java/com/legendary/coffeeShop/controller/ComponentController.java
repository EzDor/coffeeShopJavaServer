package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.controller.form.NewComponentForm;
import com.legendary.coffeeShop.controller.form.UpdateComponentForm;
import com.legendary.coffeeShop.dao.entities.Component;
import com.legendary.coffeeShop.service.ComponentService;
import com.legendary.coffeeShop.service.ValidationService;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/component")
public class ComponentController {

    private final ComponentService componentService;

    private final ValidationService validationService;

    @Autowired
    public ComponentController(ComponentService componentService, ValidationService validationService) {
        this.componentService = componentService;
        this.validationService = validationService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseBody
    public List<Component> getComponents() {
        return componentService.getComponents();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @ResponseBody
    public Status createComponent(@RequestBody NewComponentForm newComponentForm) {
        validationService.validateComponentForm(newComponentForm);
        componentService.createComponent(newComponentForm);
        return new Status("Component created successfully.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    @ResponseBody
    public Status updateComponent(@RequestBody UpdateComponentForm updateComponentForm) {

        validationService.validateComponentForm(updateComponentForm.getUpdatedComponentDetails());
        componentService.updateComponent(updateComponentForm);
        return new Status("Component created successfully.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete")
    @ResponseBody
    public Status deleteComponent(@RequestBody String name) {

        componentService.deleteComponent(name);
        return new Status("component deleted successfully.");
    }
}
