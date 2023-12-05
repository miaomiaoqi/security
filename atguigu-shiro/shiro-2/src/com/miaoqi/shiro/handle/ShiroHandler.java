package com.miaoqi.shiro.handle;

import com.miaoqi.shiro.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


/**
 * @author miaoqi
 * @date 2017-10-23 下午7:06
 **/
@Controller
@RequestMapping("/shiro")
public class ShiroHandler {

    @Autowired
    private ShiroService shiroService;

    @RequestMapping("/testShiroAnnotation")
    private String testShrioAnnotation(HttpSession session) {
        session.setAttribute("key", "value12345");
        shiroService.testMethod();
        return "redirect:/list.jsp";
    }

    @RequestMapping("/login")
    public String login(String username, String password) {
        Subject current = SecurityUtils.getSubject();
        // 未认证
        if (!current.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordTokendu 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // remember me
            token.setRememberMe(true);
            // 执行登录
            try {
                current.login(token);
            } catch (AuthenticationException ae) {
                System.out.println("登录失败: " + ae.getMessage());
            }
        }
        return "redirect:/list.jsp";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject current = SecurityUtils.getSubject();
        current.logout();
        return "redirect:/login.jsp";
    }

}
