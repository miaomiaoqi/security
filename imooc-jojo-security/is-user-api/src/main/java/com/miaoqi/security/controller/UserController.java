package com.miaoqi.security.controller;

import com.miaoqi.security.service.UserService;
import com.miaoqi.security.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    // @Autowired
    // private JdbcTemplate jdbcTemplate;
    // @Autowired
    // private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public void login(@Validated UserInfo user, HttpServletRequest request) {
        UserInfo info = this.userService.login(user);
        // 理论上登录请求是不会携带 JSESSIONID 的, false 参数可以保证在没有 session 的情况下就不创建新的 session
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 如果这里获取到了 session, 可以认为是非法的, 直接置为失效, 再次从新获取一个新的 session, 避免了 session 固定攻击
            session.invalidate();
        }
        request.getSession(true).setAttribute("user", info);
    }

    @GetMapping("/logout")
    public void login(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    @PostMapping
    public UserInfo create(@RequestBody @Validated UserInfo user) {
        return this.userService.create(user);
    }

    @PutMapping("/{id}")
    public UserInfo update(@RequestBody UserInfo user) {
        return this.userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.userService.delete(id);
    }

    @GetMapping("/{id}")
    public UserInfo get(@PathVariable Long id, HttpServletRequest request) {
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        if (user == null || !user.getId().equals(id)) {
            throw new RuntimeException("身份认证信息异常, 获取用户信息失败");
        }
        return this.userService.get(id);
    }

    @GetMapping
    public List<UserInfo> query(String name) {
        return this.userService.query(name);
    }

}
