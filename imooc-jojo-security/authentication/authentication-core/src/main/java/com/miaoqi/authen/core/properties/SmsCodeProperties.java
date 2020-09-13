/**
 *
 */
package com.miaoqi.authen.core.properties;

/**
 * @author zhailiang
 *
 */
public class SmsCodeProperties {

    /**
     * 默认配置
     */
    private int length = 6;
    private int expireIn = 60;
    private String url;

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return this.expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
