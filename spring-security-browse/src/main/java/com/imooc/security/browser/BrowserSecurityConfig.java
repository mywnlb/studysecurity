package com.imooc.security.browser;

import com.imooc.security.browser.authentication.SelfAuthenticationFailureHandler;
import com.imooc.security.browser.authentication.SelfAuthenticationSuccessHandler;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * describe:
 *
 * @author lb
 * @date 2018/09/25
 */
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPassword(){
        return  new BCryptPasswordEncoder();
    }

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SelfAuthenticationSuccessHandler selfAuthenticationSuccessHandler;

    @Autowired
    private SelfAuthenticationFailureHandler selfAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //图形验证码处理器
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(selfAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
             .formLogin()
             .loginPage("/authentication/require")
             .loginProcessingUrl("/authentication/form")
             .successHandler(selfAuthenticationSuccessHandler)
             .failureHandler(selfAuthenticationFailureHandler)
             .and()
             .authorizeRequests()
             .antMatchers(securityProperties.getBrowser().getSignUpUrl(),"/authentication/require","/code/image").permitAll()
             .anyRequest()
             .authenticated()
             .and()
             .csrf().disable();
    }
}
