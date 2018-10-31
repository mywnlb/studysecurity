package com.imooc.security.core.validate.code;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.image.ImageCodeGenerator;
import com.imooc.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.imooc.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * describe: 目的是让校验码生成器的实现变成可配置的 使用ConditionalOnMissingBean 的目的是当在容器中差找不到 imageCodeGererator的对象时，调用此注解修饰的方法
 *
 * @author lb
 * @date 2018/09/29
 */

@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @Author lb
     * @Description 图形验证码生成逻辑
     * @Date 14:41 2018/9/29
     * @Param
     * @return
     **/
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator(){
        ImageCodeGenerator imageCodeGererator = new ImageCodeGenerator();
        imageCodeGererator.setSecurityProperties(securityProperties);
        return imageCodeGererator;
    }

    /**
     * @Author lb
     * @Description 短信验证码发送逻辑
     * @Date 14:42 2018/9/29
     * @Param
     * @return
     **/
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeGererator(){
        DefaultSmsCodeSender imageCodeGererator = new DefaultSmsCodeSender();
        return imageCodeGererator;
    }
}
