package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.controller.form.ProductForm;
import com.legendary.coffeeShop.dao.entities.Component;
import com.legendary.coffeeShop.service.ComponentService;
import com.legendary.coffeeShop.service.ValidationService;
import com.legendary.coffeeShop.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.Set;

@RestController
@RequestMapping("/component")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @Autowired
    private ValidationService validationService;

    @GetMapping("/type")
    @ResponseBody
    public Set<Component> getComponentByType() {

        return componentService.getComponentByType();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @ResponseBody
    public Status createComponent(@RequestBody ComponentForm componentForm) {
        try {
            validationService.validateProductForm(componentForm);
        } catch (InputMismatchException err) {
            return new Status(err);
        }
        return componentService.createComponent(componentForm);
    }
}
