package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.controller.form.NewComponentForm;
import com.legendary.coffeeShop.controller.form.UpdateComponentForm;
import com.legendary.coffeeShop.dao.entities.Component;
import com.legendary.coffeeShop.service.ComponentService;
import com.legendary.coffeeShop.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity createComponent(@RequestBody NewComponentForm newComponentForm) {
        try {
            validationService.validateComponentForm(newComponentForm);
            componentService.createComponent(newComponentForm);
            return ResponseEntity.status(HttpStatus.OK).body("Component created successfully.");
        } catch (InputMismatchException | IllegalArgumentException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity updateComponent(@RequestBody UpdateComponentForm updateComponentForm) {
        try {
            validationService.validateComponentForm(updateComponentForm.getUpdatedComponentDetails());
            componentService.updateComponent(updateComponentForm);
            return ResponseEntity.status(HttpStatus.OK).body("Component created successfully.");
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity deleteComponent(@PathVariable String name) {
        try {
            componentService.deleteComponent(name);
            return ResponseEntity.status(HttpStatus.OK).body("component deleted successfully.");
        } catch (NoSuchElementException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
        }
    }
}
