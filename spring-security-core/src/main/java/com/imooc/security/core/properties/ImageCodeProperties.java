package com.imooc.security.core.properties;

/**
 * describe: 图形验证码大小 长度
 *
 * @author lb
 * @date 2018/09/28
 */
public class ImageCodeProperties {
    private int width = 67;
    private int height = 23;
    private int length = 4;
    private int exprein  = 60;

    /**
     * 用以配置那些请求需要验证码
     */
    private String urls;

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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExprein() {
        return exprein;
    }

    public void setExprein(int exprein) {
        this.exprein = exprein;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
