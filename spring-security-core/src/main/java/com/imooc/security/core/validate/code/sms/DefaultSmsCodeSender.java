package com.imooc.security.core.validate.code.sms;

/**
 * describe: 短信验证码默认实现
 *
 * @author lb
 * @date 2018/09/29
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("电话号码为"+mobile+"的手机，发送验证码为"+code);
    }
}
