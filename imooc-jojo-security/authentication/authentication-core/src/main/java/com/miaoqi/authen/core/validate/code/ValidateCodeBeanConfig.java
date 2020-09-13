package com.miaoqi.authen.core.validate.code;

import com.miaoqi.authen.core.properties.SecurityProperties;
import com.miaoqi.authen.core.validate.code.image.ImageCodeGenerator;
import com.miaoqi.authen.core.validate.code.sms.DefaultSmsCodeSender;
import com.miaoqi.authen.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 采用这种 @Bean 的配置方式, 可以限制条件, 更灵活的配置生成器
     *
     * @author miaoqi
     * @date 2020-03-15
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(this.securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

}
