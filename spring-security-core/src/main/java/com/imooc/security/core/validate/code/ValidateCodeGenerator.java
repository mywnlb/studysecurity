package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * describe:校验码生成器
 *
 * @author lb
 * @date 2018/09/29
 */
public interface ValidateCodeGenerator {
    ValidateVode gererate(ServletWebRequest request);
}
