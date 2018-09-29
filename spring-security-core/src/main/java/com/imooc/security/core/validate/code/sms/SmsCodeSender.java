package com.imooc.security.core.validate.code.sms;

/**
 * describe: 短信验证码发送
 *
 * @author lb
 * @date 2018/09/29
 */
public interface SmsCodeSender {

    void send(String mobile,String code);
}
