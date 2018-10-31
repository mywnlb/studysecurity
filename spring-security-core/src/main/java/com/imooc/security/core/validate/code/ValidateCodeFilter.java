package com.imooc.security.core.validate.code;

import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**会被被掉一
 *  * describe: 图形验证码过滤器  继承的once过滤器保证此过滤器只次 实现此InitializingBean 是为了对该bean中一些参数都初始化完毕后对该bean中的一些属性可以赋值
 * 相对于对类初始化时init-method，spring会先调用initialzingbean中的afterpropertiesset，如果此方法无误后在调用init-method方法，且initialzingbean和spring耦合不推荐使用同样,也可以能利用
 * 实现DisposableBean类和destory-method属性，其中在类销毁的时候会先调用实现了DisposableBean的destory方法，之后在调用destory-method指定的方法
 *    被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行。PreDestroy（）方法在destroy()方法执行执行之后执行
 * @author lb
 * @date 2018/09/28
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 验证码校验失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String,ValidateCodeType> urlMap = new HashMap<>();

    /**
     *  初始化要拦截的url配置信息
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 初始化要拦截的url配置信息
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {

        super.afterPropertiesSet();

        urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);

    }

    /**
     * 讲系统中配置的需要校验验证码的URL根据校验的类型放入map
     *
     * @param urlString
     * @param type
     */
    protected void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                urlMap.put(url, type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeType type = getValidateCodeType(request);
        if( type !=null ){
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);

            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type).validate(new ServletWebRequest(request, response));
                logger.info("验证码校验通过");

            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }

        }

        filterChain.doFilter(request,response);

    }

    /**
     * @Author lb
     * @Description 获取校验码的类型，如果当前请求不需要校验，则返回null
     * @Date 11:13 2018/10/30
     * @Param
     * @return
     **/
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType codeType = null;
        if(!StringUtils.equalsIgnoreCase(request.getMethod(),"get")){
            Set<String> urls = urlMap.keySet();
            for(String url :urls){
                if(pathMatcher.match(url,request.getRequestURI())){
                    codeType = urlMap.get(url);
                }
            }
        }

        return codeType;
    }
}
