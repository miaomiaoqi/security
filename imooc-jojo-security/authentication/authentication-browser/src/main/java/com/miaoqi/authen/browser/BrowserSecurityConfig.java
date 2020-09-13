package com.miaoqi.authen.browser;

import com.miaoqi.authen.core.authentication.AbstractChannelSecurityConfig;
import com.miaoqi.authen.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.miaoqi.authen.core.properties.SecurityConstants;
import com.miaoqi.authen.core.properties.SecurityProperties;
import com.miaoqi.authen.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    // @Autowired
    // private SpringSocialConfigurer imoocSocialSecurityConfig;

    // @Autowired
    // private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    // @Autowired
    // private InvalidSessionStrategy invalidSessionStrategy;

    /**
     * 配置记住我
     *
     * @author miaoqi
     * @date 2020-03-07
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(this.dataSource);
        // tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //
    //     // 图形验证码过滤器
    //     ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
    //     validateCodeFilter.setAuthenticationFailureHandler(this.imoocAuthenticationFailureHandler);
    //     validateCodeFilter.setSecurityProperties(this.securityProperties);
    //     validateCodeFilter.afterPropertiesSet();
    //
    //     // 短信验证码过滤器
    //     SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
    //     smsCodeFilter.setAuthenticationFailureHandler(this.imoocAuthenticationFailureHandler);
    //     smsCodeFilter.setSecurityProperties(this.securityProperties);
    //     smsCodeFilter.afterPropertiesSet();
    //
    //     http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
    //             .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
    //             // 使用表单认证
    //             .formLogin()
    //             // 如果需要验证就跳转到后端接口, 返回 json, 达到前后端分离效果
    //             .loginPage("/authentication/require")
    //             // 默认是/login 处理登录请求, 修改为/authentication/form 来处理登录请求
    //             .loginProcessingUrl("/authentication/form")
    //             // 表单登录成功的处理器
    //             .successHandler(this.imoocAuthenticationSuccessHandler)
    //             // 表单登录失败的处理器
    //             .failureHandler(this.imoocAuthenticationFailureHandler)
    //             // http.httpBasic()
    //             .and()
    //             // 配置记住我功能
    //             .rememberMe()
    //             // 配置 tokenRepository 存入数据库
    //             .tokenRepository(this.persistentTokenRepository())
    //             // 配置过期秒数
    //             .tokenValiditySeconds(this.securityProperties.getBrowser().getRememberMeSeconds())
    //             // 配置为哪个用户生成记住我功能的数据
    //             .userDetailsService(this.userDetailsService).and()
    //             // 下边是需要授权的请求
    //             .authorizeRequests()
    //             // 下边的路径不需要认证
    //             .antMatchers("/authentication/require", this.securityProperties.getBrowser().getLoginPage(), "/code/*", "/hello").permitAll()
    //             // 任何请求
    //             .anyRequest()
    //             // 都需要认证
    //             .authenticated().and()
    //             // 关闭跨站攻击
    //             .csrf().disable()
    //             // 相当于将 smsCodeAuthenticationSecurityConfig 中的配置全部加了进来
    //             .apply(smsCodeAuthenticationSecurityConfig);
    // }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)
                // .and()
                // .apply(imoocSocialSecurityConfig)
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                // .sessionManagement()
                // .invalidSessionStrategy(invalidSessionStrategy)
                // .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                // .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                // .expiredSessionStrategy(sessionInformationExpiredStrategy)
                // .and()
            .authorizeRequests()
                .antMatchers(
                    SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                    SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                    securityProperties.getBrowser().getLoginPage(),
                    SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*")
                        // securityProperties.getBrowser().getSignUpUrl(),
                        // securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
                        // securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html",
                        // "/user/regist")
                    .permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .csrf().disable();

    }




}
