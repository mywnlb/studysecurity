package com.imooc.security.core.social.connet;

import com.imooc.security.core.social.aqi.QQ;
import com.imooc.security.core.social.aqi.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * describe: 继承此类用以发送请求获取accesstoken
 *
 * @author lb
 * @date 2018/10/31
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ>{

    private String appId;

    /**
     *  请求授权地址 此处获取授权码
     */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 利用授权码获取accesstoken
     */
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    /**
     * @Author lb
     * @Description
     * @Date 19:28 2018/10/31
     * @Param
     * @return
     **/
    public QQServiceProvider(String applD,String appSecret) {

        super(new OAuth2Template(applD, appSecret,  URL_AUTHORIZE,  URL_ACCESS_TOKEN));

        this.appId = applD;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
