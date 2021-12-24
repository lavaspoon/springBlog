package com.lava.springBlog.springBlog.filter;

import com.lava.springBlog.springBlog.model.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {
    //로그인 필요없는 URL 목록 정으
    private static final String[] whiteList = {"/", "/html/Login/login", "/html/Members/addMember", "/css/*", "/javascript/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작{}", requestURI);
            //requestURI가 whiteList에 포함되는지 확인 -> whiteList가 아니면 아래 로직 실행
            if(isLoginCheckPath(requestURI)){
                log.info("인증 체크 로직 실행 {}", requestURI);
                //세션을 가져온다. false -> 세션 없으면 새로 만드는거 방지
                HttpSession session = httpRequest.getSession(false);
                //가져온 세션이 null 이거나, "loginMember" 세션이 null 이면 아래 로직 실행
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    //로그인으로 리다이렉트(로그인 후, 원래있던 페이지로 다시 리다이렉트)
                    httpResponse.sendRedirect("/html/Login/login?redirectURL=" + requestURI);
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e){
            throw e;
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    /**
     * 화이트리스트이면, 인증체크 안함
     */
    private boolean isLoginCheckPath(String requsetURI){
        return !PatternMatchUtils.simpleMatch(whiteList, requsetURI);
    }
}
