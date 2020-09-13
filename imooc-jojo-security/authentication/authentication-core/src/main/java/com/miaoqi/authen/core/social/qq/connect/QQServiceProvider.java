package com.miaoqi.authen.core.social.qq.connect;

import com.miaoqi.authen.core.social.qq.api.QQ;
import com.miaoqi.authen.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * 泛型声明 API 接口的类型, 我们是做 QQ 登录, 所以声明为 QQ
 * 封装 OAuth 的 1~6 步流程, 获取 Connect
 *
 * @author miaoqi
 * @date 2020/4/4
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        // OAuth2Template, 封装了 1~5 步, 是固定流程
        // appId: QQ 互联的用户名
        // appSecret: QQ 互联的密码
        // authorizeUrl: 第 1 步, 认证服务器的地址
        // accessTokenUrl: 第 4 步, 获取令牌的地址
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, this.appId);
    }

}
