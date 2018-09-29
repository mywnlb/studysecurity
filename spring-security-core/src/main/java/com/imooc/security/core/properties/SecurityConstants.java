package com.imooc.security.core.properties;

/**
 * describe: 关键字
 *
 * @author lb
 * @date 2018/09/29
 */
public interface SecurityConstants {
    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

}
