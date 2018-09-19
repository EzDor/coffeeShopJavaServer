package com.legendary.coffeeShop.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CommonConstants {

    @Value("${user_service.admin_permission}")
    private String adminPermission;

    @Value("${user_service.user_permission}")
    private String userPermission;

    @Value("${product_sort_key}")
    private String productSortKey;

    @Value("${component_sort_key}")
    private String componentSortKey;

    @Value("${user_sort_key}")
    private String userSortKey;
}
