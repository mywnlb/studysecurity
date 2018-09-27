/**
 * 
 */
package com.imooc.security.core;

import com.imooc.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lb
 * @Description 添加此注解可以让 SecurityProperties加入ioc控制
 * @Date 17:59 2018/9/27
 * @Param
 * @return
 **/
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
