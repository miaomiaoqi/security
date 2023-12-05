package com.miaoqi.authen.core.social.qq.connect;

import com.miaoqi.authen.core.social.qq.api.QQ;
import com.miaoqi.authen.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * ApiAdapter实现
 *
 * @author miaoqi
 * @date 2020/4/20
 */
public class QQAdapter implements ApiAdapter<QQ> {

    /**
     * 测试 QQ 是否可用
     *
     * @author miaoqi
     * @date 2020-04-20
     *
     * @return
     */
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getuserInfo();

        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {
        // do noting
    }

}
