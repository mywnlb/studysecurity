package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * describe: 校验码存取器
 *
 * @author lb
 * @date 2018/09/29
 */
public interface ValidateCodeRepository {
    /**
     * 保存验证码
     * @param request
     * @param code
     * @param validateCodeType
     */
    void save(ServletWebRequest request, ValiDateVode code, ValidateCodeType validateCodeType);
    /**
     * 获取验证码
     * @param request
     * @param validateCodeType
     * @return
     */
    ValiDateVode get(ServletWebRequest request, ValidateCodeType validateCodeType);
    /**
     * 移除验证码
     * @param request
     * @param codeType
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);

}
