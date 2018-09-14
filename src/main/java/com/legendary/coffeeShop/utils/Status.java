package com.legendary.coffeeShop.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
public class Status {

    private String type;
    private String message;
    private boolean success = false;
    private String error;
    private Map<String, Object> data = new HashMap<String, Object>();

    public Object getAttr(String key) {
        return data.get(key);
    }

    public void addAttr(String key, Object value) {
        data.put(key, value);
    }

    public Status(String message) {
        this.message = message;
        this.success = true;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
