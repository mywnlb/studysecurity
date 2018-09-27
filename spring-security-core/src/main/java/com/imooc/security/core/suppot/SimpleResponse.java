package com.imooc.security.core.suppot;

/**
 * describe:
 *
 * @author lb
 * @date 2018/09/27
 */
public class SimpleResponse {
    public SimpleResponse(Object content) {
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
