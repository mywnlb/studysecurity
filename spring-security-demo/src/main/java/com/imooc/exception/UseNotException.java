package com.imooc.exception;

public class UseNotException extends RuntimeException{
    private String params;
    public UseNotException(String params) {
        super("this is self exception");
        this.params = params;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
