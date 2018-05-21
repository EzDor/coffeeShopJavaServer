package com.legendary.coffeeShop.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CommonConstants {

    @Value("${test_common_constants}")
    private String testCommonConstants;
}
