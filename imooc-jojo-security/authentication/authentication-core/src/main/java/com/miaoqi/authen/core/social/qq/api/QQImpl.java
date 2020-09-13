package com.miaoqi.authen.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * 第 6 步的实现, 获取用户资源, 继承 AbstractOAuth2ApiBinding
 *
 * @author miaoqi
 * @date 2020/3/29
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        // 以参数形式发送 accessToken
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = this.getRestTemplate().getForObject(url, String.class);

        System.out.println(result);

        this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");
    }

    @Override
    public QQUserInfo getuserInfo() throws IOException {
        String url = String.format(URL_GET_USERINFO, this.appId, this.openId);
        // SpringSocial 会帮我们自动带上 accessToken 所以不用拼接
        String result = this.getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        return this.objectMapper.readValue(result, QQUserInfo.class);
    }

}
