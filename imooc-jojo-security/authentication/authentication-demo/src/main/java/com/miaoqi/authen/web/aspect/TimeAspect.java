/**
 *
 */
package com.miaoqi.authen.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.util.Date;

/**
 * @author zhailiang
 *
 */
// @Aspect
// @Component
public class TimeAspect {

    @Around("execution(* com.miaoqi.authen.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }

        long start = System.currentTimeMillis();

        Object object = pjp.proceed();

        System.out.println("time aspect 耗时:" + (new Date().getTime() - start));

        System.out.println("time aspect end");

        return object;
    }

}
