package com.miaoqi.authen.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器, 封装不同校验码的处理逻辑
 *
 * @author miaoqi
 * @date 2020/3/7
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入 session 时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     *
     * @author miaoqi
     * @date 2020-03-07
     *
     * @return
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @author miaoqi
     * @date 2020-03-15
     *
     * @return
     */
    void validate(ServletWebRequest servletWebRequest);

}
