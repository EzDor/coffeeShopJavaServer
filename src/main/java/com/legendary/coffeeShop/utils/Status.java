package com.legendary.coffeeShop.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
public class Status {
    public static final String OK = "ok";
    public static final String ERROR = "error";

    private String status;
    private String type;
    private String msg;
    private boolean success = false;
    private String error;
    private Map<String, Object> data = new HashMap<String, Object>();
    private Object files;

    public Object getAttr(String key) {
        return data.get(key);
    }

    public void addAttr(String key, Object value) {
        data.put(key, value);
    }

    public Status(String status) {
        this.status = status;
        if (status.equals(OK))
            success = true;
    }

    public Status(String status, String msg) {
        this.status = status;
        this.msg = msg;

        if (status.equals(OK))
            success = true;
    }

    public Status(Exception exception){
        this.status = ERROR;
        this.type = exception.getClass().toString();
        this.msg = exception.getMessage();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
