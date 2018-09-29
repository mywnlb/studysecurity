package com.imooc.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * describe: 验证码的验证支持
 *
 * @author lb
 * @date 2018/09/29
 * @see  org.springframework.security.authentication.dao.DaoAuthenticationProvider
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;

    /**
     * @Author lb
     * @Description 利用userDetailsService 查找用户信息并组装再返回
     * @Date 18:09 2018/9/29
     * @Param
     * @return
     **/
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken)authentication;
        UserDetails user = userDetailsService.loadUserByUsername((String) smsCodeAuthenticationToken.getPrincipal());
        if(user == null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        SmsCodeAuthenticationToken smsCodeAuthenticationResult = new SmsCodeAuthenticationToken(user,user.getAuthorities());

        smsCodeAuthenticationResult.setDetails(smsCodeAuthenticationToken.getDetails());

        return smsCodeAuthenticationResult;
    }

    /**
     * @Author lb
     * @Description 用以判断是否支持验证码逻辑
     * @Date 18:07 2018/9/29
     * @Param
     * @return
     **/
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
