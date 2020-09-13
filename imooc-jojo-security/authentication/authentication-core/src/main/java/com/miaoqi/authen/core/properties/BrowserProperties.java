package com.miaoqi.authen.core.properties;

public class BrowserProperties {

    private String loginPage = "/imooc-signIn.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600;

    public String getLoginPage() {
        return this.loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return this.loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return this.rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

}
