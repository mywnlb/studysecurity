package com.imooc.security.core.validate.code;

import com.imooc.security.core.properties.SecurityProperties;
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

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGererator")
    public ValidateCodeGenerator imageCodeGererator(){
        ImageCodeGererator imageCodeGererator = new ImageCodeGererator();
        imageCodeGererator.setSecurityProperties(securityProperties);
        return imageCodeGererator;
    }
}
