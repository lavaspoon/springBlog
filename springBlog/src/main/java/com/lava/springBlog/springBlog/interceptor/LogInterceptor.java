package com.lava.springBlog.springBlog.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logid";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        request.setAttribute(LOG_ID, uuid);
        log.info("REQUEST [{}][{}][{}]", requestURI, uuid, handler);

        if(handler instanceof MethodHandle) {
            HandlerMethod hm = (HandlerMethod) handler;//호출한 컨트롤러 메서드를 모두 가지고 있음
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        Object logid = (String) request.getAttribute(LOG_ID);

        log.info("RESPONSE [{}][{}][{}]", logid, requestURI, handler);

        if(ex != null) {
            log.error("afterCompletion error", ex);
        }
    }
}
