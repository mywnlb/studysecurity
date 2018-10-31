package com.imooc.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * describe: 校验码处理器管理器
 *
 * @author lb
 * @date 2018/10/28
 */
@Component
public class ValidateCodeProcessorHolder {
    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessors;

    /**
     *  根据类型查找 校验码处理器
     * @return
     */
    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){
        return  findValidateCodeProcessor(type.toString().toLowerCase());
    }

    /**
     *  重载上一个方法
     * @param type
     * @return
     */
    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type+ ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessors.get(name);
        if(validateCodeProcessor == null){
            throw  new ValidateCodeException("验证码处理器"+name+"不存在");
        }

        return validateCodeProcessor;
    }
}
