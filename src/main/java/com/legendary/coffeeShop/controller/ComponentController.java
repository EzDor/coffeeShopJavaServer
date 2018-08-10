package com.legendary.coffeeShop.controller;

import com.legendary.coffeeShop.controller.form.ComponentForm;
import com.legendary.coffeeShop.service.ComponentService;
import com.legendary.coffeeShop.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/component")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @Autowired
    private ValidationService validationService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity createComponent(@RequestBody ComponentForm componentForm) {
        try {
            validationService.validateComponentForm(componentForm);
            componentService.createComponent(componentForm);
            return ResponseEntity.status(HttpStatus.OK).body("Component created successfully.");
        } catch (InputMismatchException|IllegalArgumentException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity updateComponent(@RequestBody ComponentForm componentForm) {
        try {
            validationService.validateComponentForm(componentForm);
            componentService.updateComponent(componentForm);
            return ResponseEntity.status(HttpStatus.OK).body("Component created successfully.");
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{name}")
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
