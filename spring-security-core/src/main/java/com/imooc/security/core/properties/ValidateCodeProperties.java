package com.imooc.security.core.properties;

/**
 * describe: 验证码配置
 *
 * @author lb
 * @date 2018/09/28
 */
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
