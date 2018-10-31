package com.imooc.security.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * describe: 验证码基类
 *
 * @author lb
 * @date 2018/09/29
 */
public class ValiDateVode implements Serializable {

    private static final long serialVersionUID = 5024357573360737222L;

    public ValiDateVode(String code, int expireIn) {
        this.code = code;
        this.expireTiem = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValiDateVode(String code, LocalDateTime expireTiem) {
        this.code = code;
        this.expireTiem = expireTiem;
    }

    /**
     * 验证码
     */
    private String code;

    /**
     *  失效时间
     */
    private LocalDateTime expireTiem;

    /**
     *
     * 是否失效
     */
    public boolean IsExpired(){
        return LocalDateTime.now().isAfter(expireTiem);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTiem() {
        return expireTiem;
    }

    public void setExpireTiem(LocalDateTime expireTiem) {
        this.expireTiem = expireTiem;
    }
}
