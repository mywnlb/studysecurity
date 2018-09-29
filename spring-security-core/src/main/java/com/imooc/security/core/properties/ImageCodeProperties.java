package com.imooc.security.core.properties;

/**
 * describe: 图形验证码大小 长度
 *
 * @author lb
 * @date 2018/09/28
 */
public class ImageCodeProperties  extends SmsCodeProperties{
    private int width = 67;
    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
