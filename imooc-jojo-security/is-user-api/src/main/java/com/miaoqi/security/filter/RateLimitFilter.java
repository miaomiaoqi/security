package com.miaoqi.security.filter;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * OncePerRequestFilter, 保证过滤器在一个请求中只执行一次
 *
 * @author miaoqi
 * @date 2020/2/27
 */
@Component
@Order(1)
public class RateLimitFilter extends OncePerRequestFilter {

    private RateLimiter rateLimitFilter = RateLimiter.create(1D);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        if (this.rateLimitFilter.tryAcquire()) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("too many request!!!");
            response.getWriter().flush();
            return;
        }
    }

}
