package com.miaoqi.security.filter;

import com.miaoqi.security.log.AuditLog;
import com.miaoqi.security.log.AuditLogRepository;
import com.miaoqi.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuditLogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuditLog auditLog = new AuditLog();
        auditLog.setMethod(request.getMethod());
        auditLog.setPath(request.getRequestURI());
        User user = (User) request.getAttribute("user");
        if (user != null) {
            auditLog.setUsername(user.getUsername());
        }
        this.auditLogRepository.save(auditLog);
        request.setAttribute("auditLogId", auditLog.getId());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long auditLogId = (Long) request.getAttribute("auditLogId");
        AuditLog auditLog = this.auditLogRepository.findById(auditLogId).get();
        auditLog.setStatus(response.getStatus());
        this.auditLogRepository.save(auditLog);
    }

}
