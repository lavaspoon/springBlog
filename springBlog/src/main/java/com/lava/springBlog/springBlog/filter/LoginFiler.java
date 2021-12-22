package com.lava.springBlog.springBlog.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LoginFiler implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");
        //ServletRequest는 기능이 별로 없어서 부모인 HttpServletRequest로 다운캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        //사용자 구분용
        String uuid = UUID.randomUUID().toString();

        try {
            log.info("REQUEST: [{}],[{}]", uuid, requestURI);
            //중요 -> 다음 필터 호출, 없으면 서블릿 호출 (HTTP 요청 -> WAS -> filter -> servlet -> controller)
            chain.doFilter(request, response);
        }catch (Exception e){
            throw e;
        }finally {
            log.info("RESPONSE: [{}],[{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
