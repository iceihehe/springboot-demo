package com.iceihehe.cm.web.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.logging.Logger;

public class LoginInterceptor implements HandlerInterceptor {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private HashMap<String, String> cookieHashMap = new HashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("----------登录验证----------");
        return true;
    }
}
