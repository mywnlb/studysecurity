package com.imooc.security.core.validate.code;

import com.imooc.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * describe: 验证码处理器
 *
 * @author lb
 * @date 2018/09/28
 */
@RestController
public class ValidateCodeController {
    private static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGererator;

    @Autowired
    private ValidateCodeGenerator smsCodeGererator;

    @Autowired
    private SmsCodeSender smsCodeSender;


    /**
     * @Author lb
     * @Description 图片验证码
     * @Date 14:45 2018/9/29
     * @Param
     * @return
     **/
    @GetMapping("/code/image")
    public void createCodeimage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) imageCodeGererator.gererate(new ServletWebRequest(request));

        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);

        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }

    /**
     * @Author lb
     * @Description 短信验证码
     * @Date 14:46 2018/9/29
     * @Param
     * @return
     **/
    @GetMapping("/code/sms")
    public void createCodeSms(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        ValidateVode smsCode = smsCodeGererator.gererate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,smsCode);

        String mobile = ServletRequestUtils.getRequiredStringParameter(request,"mobile");

        smsCodeSender.send(mobile,smsCode.getCode());
    }

}
