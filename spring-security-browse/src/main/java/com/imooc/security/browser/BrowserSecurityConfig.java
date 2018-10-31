package com.imooc.security.browser;

import com.imooc.security.browser.authentication.SelfAuthenticationFailureHandler;
import com.imooc.security.browser.authentication.SelfAuthenticationSuccessHandler;
import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidataCodeSecurityConfig;
import com.imooc.security.core.validate.code.ValidateCodeBeanConfig;
import com.imooc.security.core.validate.code.ValidateCodeFilter;
import com.imooc.security.core.validate.code.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * describe:
 *
 * @author lb
 * @date 2018/09/25
 */
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SelfAuthenticationSuccessHandler selfAuthenticationSuccessHandler;

    @Autowired
    private SelfAuthenticationFailureHandler selfAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ValidataCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    /**
     *
     * 密码加密解密
     */
    @Bean
    public PasswordEncoder getPassword(){
        return  new BCryptPasswordEncoder();
    }

    /**
     *
     *  读取数据库 用以实现记住我的功能 记住我功能会将用户信息写入数据库中用以下次登录时在数据库中查询token在根据token查询用户
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);

        //当启动的时候去数据库创建记住我功能的保存表
       // tokenRepository.setCreateTableOnStartup(true);

        return tokenRepository;
    }

    /**
     * @Author lb
     * @Description url权限配置
     * @Date 9:11 2018/9/29
     * @Param
     * @return
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.
                apply(smsCodeAuthenticationSecurityConfig)
                .and()
             //验证码过滤器配置
                .apply(validateCodeSecurityConfig)
                .and()
             //登录配置
                 .formLogin()
                 .loginPage("/authentication/require")
                 .loginProcessingUrl("/authentication/form")
                 .successHandler(selfAuthenticationSuccessHandler)
                 .failureHandler(selfAuthenticationFailureHandler)
             .and()
             //记住我
             .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRembermeSeconds())
                .userDetailsService(userDetailsService)
             .and()
             //路由匹配
             .authorizeRequests()
                 .antMatchers(securityProperties.getBrowser().getSignUpUrl(),"/authentication/*","/code/*").permitAll()
                 .anyRequest()
                 .authenticated()
             .and()
             .csrf().disable();
    }
}
