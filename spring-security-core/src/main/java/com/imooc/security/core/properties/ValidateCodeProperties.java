package com.imooc.security.core.properties;

/**
 * describe: 验证码配置
 *
 * @author lb
 * @date 2018/09/28
 */
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
