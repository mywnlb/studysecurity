package com.imooc.security.core.validate.code.image;

import com.imooc.security.core.validate.code.ValiDateVode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * describe:
 *
 * @author lb
 * @date 2018/09/28
 */
public class ImageCode extends ValiDateVode {

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTiem) {
        super(code,expireTiem);
        this.image = image;
    }

    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
