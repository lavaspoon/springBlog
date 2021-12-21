package com.lava.springBlog.springBlog.session;

import com.lava.springBlog.springBlog.model.MemberVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {
    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest(){
        //1. 세션 생성 (서버 응답-> 클라이언트)
        MemberVO memberVO = new MemberVO();
        //스프링에서 httpservlet response, request 테스트 용 지원 Mock
        MockHttpServletResponse response = new MockHttpServletResponse();
        //서버에서 세션과 쿠키를 만들어서 reponse 에 담는다. 서버 입장에서는 웹브라우저에 응답이 나간것
        sessionManager.createSession(memberVO, response);

        //요청에 응답 쿠키 저장 (클라이언트 요청-> 서버)
        MockHttpServletRequest request = new MockHttpServletRequest();
        //응답받은것에서 쿠키를 가져와서 요청에 쿠키 저장
        request.setCookies(response.getCookies());

        //2. 세션 조회
        Object result = sessionManager.getSession(request);
        Assertions.assertThat(result).isEqualTo(memberVO);

        //3. 세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        Assertions.assertThat(expired).isNull();
    }
}