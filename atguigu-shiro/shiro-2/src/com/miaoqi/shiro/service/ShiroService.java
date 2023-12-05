package com.miaoqi.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

/**
 * @author miaoqi
 * @date 2017-10-24 下午5:39
 **/
public class ShiroService {

    @RequiresRoles({"admin"})
    public void testMethod() {
        System.out.println("testMethod");
        Session session = SecurityUtils.getSubject().getSession();
        Object value = session.getAttribute("key");

        System.out.println("Service SessionVal: " + value);
    }

}
