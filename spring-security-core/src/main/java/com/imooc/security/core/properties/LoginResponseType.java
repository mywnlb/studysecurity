package com.imooc.security.core.properties;

/**
 * describe: 认证失败或者成功之后是选择自定义的json还是默认的跳转
 *
 * @author lb
 * @date 2018/09/27
 */
public enum  LoginResponseType {
    /**
     * 跳转
     */
    REDIRECT,

    /**
     * json
     */
    JSON
}
