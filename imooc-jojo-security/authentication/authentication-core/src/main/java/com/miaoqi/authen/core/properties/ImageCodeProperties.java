/**
 *
 */
package com.miaoqi.authen.core.properties;

/**
 * @author zhailiang
 *
 */
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        this.setLength(4);
    }

    /**
     * 默认配置
     */
    private int width = 67;
    private int height = 23;

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
