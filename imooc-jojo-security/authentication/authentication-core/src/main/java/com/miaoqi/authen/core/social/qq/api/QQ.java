package com.miaoqi.authen.core.social.qq.api;

import java.io.IOException;

/**
 * https://connect.qq.com/index.html
 *
 * @author miaoqi
 * @date 2020/3/29
 */
public interface QQ {

    QQUserInfo getuserInfo() throws IOException;

}
