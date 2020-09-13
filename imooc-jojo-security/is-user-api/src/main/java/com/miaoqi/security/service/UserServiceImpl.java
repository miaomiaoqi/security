package com.miaoqi.security.service;

import com.lambdaworks.crypto.SCryptUtil;
import com.miaoqi.security.dao.UserRepository;
import com.miaoqi.security.user.User;
import com.miaoqi.security.user.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfo create(UserInfo userInfo) {
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        user.setPassword(SCryptUtil.scrypt(user.getPassword(), 32768, 8, 1));
        this.userRepository.save(user);
        userInfo.setId(user.getId());
        return userInfo;
    }

    @Override
    public UserInfo update(UserInfo userInfo) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserInfo get(Long id) {
        return this.userRepository.findById(id).get().buildInfo();
    }

    @Override
    public List<UserInfo> query(String name) {
        // 使用 sql 拼接会存在 sql 注入, name=' or 1=1 or name='
        // String sql = "select id, name FROM user WHERE name = '" + name + "'";
        return null;
    }

    @Override
    public UserInfo login(UserInfo info) {
        UserInfo result = null;
        User user = this.userRepository.findByUsername(info.getUsername());
        if (user != null && SCryptUtil.check(info.getPassword(), user.getPassword())) {
            result = user.buildInfo();
        }
        return result;
    }

}
