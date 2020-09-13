package com.miaoqi.authen.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

    private BrowserProperties browser;

    private ValidateCodeProperties code;

    public BrowserProperties getBrowser() {
        return this.browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return this.code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

}
