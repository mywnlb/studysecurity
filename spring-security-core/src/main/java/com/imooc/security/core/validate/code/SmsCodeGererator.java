package com.imooc.security.core.validate.code;

import com.imooc.security.core.properties.SecurityProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Author lb
 * @Description 短信验证码生成器
 * @Date 14:49 2018/9/29
 * @Param
 * @return
 **/
@Component("smsCodeGererator")
public class SmsCodeGererator implements ValidateCodeGenerator {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateVode gererate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateVode(code,securityProperties.getCode().getSms().getExprein());
    }
}
