package com.springsecurity.lb.springsecuritydemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

//controlle 抛出的异常将会在此处接受
@ControllerAdvice
public class ContollerExceptionHandel {

    @ExceptionHandler(UseNotException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> UseNotExceptionHandle(UseNotException ex){
        Map<String,String> map = new HashMap<String, String>();
        map.put("params",ex.getParams());

        return map;
    }
}
