package com.miaoqi.authen.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        this.logger.info("登录用户名" + username);
        // 1. 根据用户名查找用户信息
        // 2. 根据查找到的用户信息判断用户是否被冻结
        //  username, password, enabled(用户是否可用), accountNonExpired(账户没过期), credentialsNonExpired(密码没过期), accountNonLocked(用户没冻结), authorities
        return new User(username, this.passwordEncoder.encode("123456"), true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

}
