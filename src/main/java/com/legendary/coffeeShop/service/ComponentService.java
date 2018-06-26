package com.legendary.coffeeShop.service;

import com.legendary.coffeeShop.dao.repositories.ComponentRepository;
import com.legendary.coffeeShop.utils.CommonConstants;
import com.legendary.coffeeShop.utils.Status;
import com.legendary.coffeeShop.dao.entities.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ComponentService {


    @Autowired
    private ComponentRepository ComponentRepository;

    @Autowired
    private CommonConstants commonConstants;


    /*********************************
     * Public Functions
     *********************************/

    public Set<Component> getSaladIngredients() {
        return new HashSet<>(ComponentRepository.findByAmountEquals("a"));
    }


}
