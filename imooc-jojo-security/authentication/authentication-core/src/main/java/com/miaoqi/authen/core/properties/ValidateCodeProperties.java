package com.miaoqi.authen.core.properties;

public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();

    public ImageCodeProperties getImage() {
        return this.image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSms() {
        return this.sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }

}
