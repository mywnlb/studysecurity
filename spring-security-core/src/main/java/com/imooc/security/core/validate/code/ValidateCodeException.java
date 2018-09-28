package com.imooc.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * describe: 图形验证码验证失败异常  继承security异常
 *
 * @author lb
 * @date 2018/09/28
 */
public class ValidateCodeException extends AuthenticationException  {

    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
