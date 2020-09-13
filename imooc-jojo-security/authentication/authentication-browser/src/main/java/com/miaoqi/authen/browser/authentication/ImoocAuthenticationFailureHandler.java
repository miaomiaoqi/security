package com.miaoqi.authen.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaoqi.authen.browser.support.SimpleResponse;
import com.miaoqi.authen.core.properties.LoginType;
import com.miaoqi.authen.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
// public class ImoocAuthenticationFailureHandler implements AuthenticationFailureHandler {
public class ImoocAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        this.logger.info("登陆失败");
        if (LoginType.JSON.equals(this.securityProperties.getBrowser().getLoginType())) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(this.objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        } else {
            // springboot 的默认错误处理方式
            super.onAuthenticationFailure(request, response, exception);
        }
    }

}
