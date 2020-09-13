package com.miaoqi.authen.browser.support;

public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return this.content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

}