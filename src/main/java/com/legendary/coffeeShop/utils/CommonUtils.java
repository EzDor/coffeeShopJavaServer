package com.legendary.coffeeShop.utils;

import org.springframework.data.domain.Sort;

public class CommonUtils {

    public static Sort sortAscBy(String key) {

        return new Sort(Sort.Direction.ASC, key);
    }

    public static Sort sortDescBy(String key) {

        return new Sort(Sort.Direction.DESC, key);
    }
}
