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
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
        return componentService.createComponent(componentForm);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity updateComponent(@RequestBody ComponentForm componentForm) {
        try {
            validationService.validateComponentForm(componentForm);
        } catch (InputMismatchException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
        return componentService.updateComponent(componentForm);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{name}")
    @ResponseBody
    public ResponseEntity updateComponent(@PathVariable String name) {
        return componentService.deleteComponent(name);
    }
}
