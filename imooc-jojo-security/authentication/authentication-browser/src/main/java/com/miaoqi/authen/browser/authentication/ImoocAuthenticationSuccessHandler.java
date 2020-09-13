package com.miaoqi.authen.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaoqi.authen.core.properties.LoginType;
import com.miaoqi.authen.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @Component
// public class ImoocAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
@Component
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        this.logger.info("登陆成功");
        if (LoginType.JSON.equals(this.securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(this.objectMapper.writeValueAsString(authentication));
        } else {
            // 采用父类的方法默认跳转上一次请求
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

}
