package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * describe: 校验码处理逻辑  封装不同的校验码处理逻辑
 *
 * @author lb
 * @date 2018/09/29
 */
public interface ValidateCodeProcessor {

    /**
     *  创建校验码
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     *  校验验证码
     * @param servletWebRequest
     */
    void validate(ServletWebRequest servletWebRequest);
}
