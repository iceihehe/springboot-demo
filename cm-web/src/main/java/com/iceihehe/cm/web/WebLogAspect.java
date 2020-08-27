package com.iceihehe.cm.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class WebLogAspect {

    Logger logger = LogManager.getLogger(this.getClass().getName());
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();

    @Pointcut("execution(public * com.iceihehe.cm.web.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        requestThreadLocal.set(request);
        String method = request.getMethod();
        String uri = request.getRequestURI();
        logger.info("{} {}", method, uri);
        String input;
        switch (method) {
            case "GET":
                input = new ObjectMapper().writeValueAsString(request.getParameterMap());
                break;
            case "POST":
                input = new ObjectMapper().writeValueAsString(joinPoint.getArgs());
                break;
            default:
                input = "";
        }
        logger.info("Input:\t{}", input);
    }

    @AfterReturning(pointcut = "webLog()", returning = "resp")
    public Object doAfterReturning(Object resp) throws Throwable {
        Long timeSpent = System.currentTimeMillis() - startTime.get();
        HttpServletRequest request = requestThreadLocal.get();
        logger.info("{} {} {} msecs", request.getMethod(), request.getRequestURI(), timeSpent);
        logger.info("Output:\t{}", new ObjectMapper().writeValueAsString(resp));
        return resp;
    }


}
