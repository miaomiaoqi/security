package com.miaoqi.shiro.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @author miaoqi
 * @date 2017-10-23 下午3:32
 **/
public class SecondRealm extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("--------SecondRealm doGetAuthenticationInfo--------");
        // 1. 把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        // 2. 从UsernamePasswordToken中来获取username
        String username = upToken.getUsername();

        // 3. 调用数据库的方法, 从数据库中查询username对应的用户记录
        System.out.println("从数据库中获取 username: " + username + " 所对应的用户信息");

        // 4. 若用户不存在则可以抛出 UnknownAccountException 异常
        if ("unknown".equals(username)) {
            throw new UnknownAccountException("用户不存在");
        }

        // 5. 根据用户信息的情况决定是否抛出其他的异常
        if ("monster".equals(username)) {
            throw new LockedAccountException("用户被锁定");
        }

        // 6. 根据用户的情况来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        // 以下信息是从数据库中获取的
        // 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的实体类对象
        Object principal = username;
        // 2). credentials: 密码.
        Object credentials = null;
        if ("admin".equals(username)) {
            credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
        } else if ("user".equals(username)) {
            credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
        }
        // 3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法
        String realmName = getName();
        // 4). 盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(principal);

        // SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realName);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt,
                realmName);
        return info;
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "SHA1";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("user");
        int hashIterations = 1024;

        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }

}
