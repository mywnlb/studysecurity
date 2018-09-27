package com.imooc.filter;

import com.imooc.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;

//假设加入过滤器连的另一种方法
//@Configuration
public class WebFilterConfig extends WebMvcConfigurerAdapter {

    //继承webmvcConfiginterceptor是为了能覆盖addinterceptor方法将拦截器加入拦截器连
    @Autowired
    private TimeInterceptor timeInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }

    @Bean
    public FilterRegistrationBean getTimeFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("/*");
        filterRegistrationBean.setUrlPatterns(arrayList);
        return filterRegistrationBean;
    }
}
