/**
 *
 */
package com.miaoqi.authen.core.validate.code.sms;

import com.miaoqi.authen.core.properties.SecurityProperties;
import com.miaoqi.authen.core.validate.code.ValidateCode;
import com.miaoqi.authen.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author zhailiang
 *
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(this.securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, this.securityProperties.getCode().getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return this.securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


}
