package com.miaoqi.security.server.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
// 作为一个授权服务器存在
@EnableAuthorizationServer
public class OAuth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 哪些用户可以访问
     *
     * @author miaoqi
     * @date 2020-02-29
     *
     * @return
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
    }

    /**
     * 哪些服务可以访问
     *
     * @author miaoqi
     * @date 2020-02-29
     *
     * @return
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("orderApp").secret(this.passwordEncoder.encode("123456")).scopes("read", "write").accessTokenValiditySeconds(
                3600).resourceIds("order-server").authorizedGrantTypes("password").and().withClient("orderService").secret(
                this.passwordEncoder.encode("123456")).scopes("read", "write").accessTokenValiditySeconds(3600).resourceIds(
                "order-server").authorizedGrantTypes("password");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }

}
