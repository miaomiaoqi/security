package com.miaoqi.security.config;

import com.miaoqi.security.filter.AclInterceptor;
import com.miaoqi.security.filter.AuditLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private AuditLogInterceptor auditLogInterceptor;
    @Autowired
    private AclInterceptor aclInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.auditLogInterceptor);
        registry.addInterceptor(this.aclInterceptor);
    }

}
