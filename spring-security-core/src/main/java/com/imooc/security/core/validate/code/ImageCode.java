package com.imooc.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * describe:
 *
 * @author lb
 * @date 2018/09/28
 */
public class ImageCode {

    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTiem = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTiem) {
        this.image = image;
        this.code = code;
        this.expireTiem = expireTiem;
    }

    private BufferedImage image;

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

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
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
