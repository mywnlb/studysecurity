package com.imooc.security.core.properties;

/**
 * describe: 短信验证码
 *
 * @author lb
 * @date 2018/09/28
 */
public class SmsCodeProperties {

    private int length = 6;
    private int exprein  = 60;

    /**
     * 用以配置那些请求需要验证码
     */
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
