package com.imooc.security.core.social.connet;

import com.imooc.security.core.social.aqi.QQ;
import com.imooc.security.core.social.aqi.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * describe: 用以将获取到的用户数据转换为social的数据结构
 *
 * @author lb
 * @date 2018/10/31
 */
public class QQAdapt implements ApiAdapter<QQ> {

    /**
     * @Author lb
     * @Description 测试获取用户信息的服务是否开可以访问，比如qq
     * @Date 19:41 2018/10/31
     * @Param
     * @return
     **/
    @Override
    public boolean test(QQ api) {
        return true;
    }

    /**
     * @Author lb
     * @Description 将获取到的用户数据转换为social的数据结构
     * @Date 19:42 2018/10/31
     * @Param
     * @return
     **/
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo qq = api.getUserInfo();

        //昵称
        values.setDisplayName(qq.getNickname());

        values.setImageUrl(qq.getFigureurl_qq_1());

        //个人主页
        values.setProfileUrl(null);

        //用户的openid
        values.setProviderUserId(qq.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
