package com.springsecurity.lb.springsecuritybrowse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
             .loginPage("/imooc-signIn.html")
             .loginProcessingUrl("/authentication/form")
             .permitAll()
             .and()
             .authorizeRequests()
             .anyRequest()
             .authenticated()
             .and()
             .csrf().disable();
    }
}
