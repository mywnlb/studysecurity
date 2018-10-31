package com.imooc.security.core.social.aqi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * describe: 继承此类用以利用accesstoken获取用户信息
 *
 * @author lb
 * @date 2018/10/31
 */

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String openId;
    private String appId;

    public QQImpl(String accessTpken,String appId) {
        super(accessTpken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        this.appId = appId;
        String url = String.format(URL_GET_OPENID,accessTpken);

        logger.info("获取用户openID的url："+url);

        //发送请求获取用户的openid
        String result = getRestTemplate().getForObject(url,String.class);

        this.openId = StringUtils.substringBetween(result,"\"openid\":","\"}");

        logger.info("获取到的用户oppenid："+openId);

    }

    @Override
    public QQUserInfo getUserInfo()  {
        String url = String.format(URL_GET_USERINFO,appId,openId);

        logger.info("获取用户信息的url："+url);

        //获取用户信息
        String result = getRestTemplate().getForObject(url,String.class);

        logger.info("获取到的用户信息："+result);

        try {
            QQUserInfo qqUserInfo =  objectMapper.readValue(result,QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
            return qqUserInfo;

        } catch (IOException e) {
            throw  new RuntimeException("获取用户信息失败");
        }

    }
}
