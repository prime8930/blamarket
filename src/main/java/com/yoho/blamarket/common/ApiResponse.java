package com.yoho.blamarket.common;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ApiResponse {

    private HttpStatus status;
    private String message;
    private Map<String, Object> data;

    public ApiResponse(){
        this.status = HttpStatus.OK;
        this.message = "";
        this.data = new HashMap<String, Object>();
    }

    public ApiResponse(HttpStatus status, String message, Map<String, Object> map){
        this.status = status;
        this.message = message;
        this.data = map;
    }

    public ApiResponse(HttpStatus status, String message){
        this.status = status;
        this.message = message;
        this.data = new HashMap<String, Object>();
    }

    public void putData(String key, Object obj){
        this.data.put(key, obj);
    }

}
