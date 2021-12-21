package com.lava.springBlog.springBlog.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 세션 관리 - 직접 만들어보는 세션
 */
@Component //스프링 빈에 등록
public class SessionManager {
    //자주 쓰니깐 상수로 등록 alt+command+c 단축키
    public static final String SESSION_COOKIE_NAME = "MY_SESSION_ID";
    //동시에 여러 요청이 올수도 있으니 HashMap 대신에 ConcurrentHashMap 사용
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * 세션 생성- 클라이언트에 response 응답
     */
    public void createSession(Object value, HttpServletResponse response){
        //세션 생성
        String sessionID = UUID.randomUUID().toString();
        sessionStore.put(sessionID, value);

        //쿠키 생성 Cookie(세션이름, UUID)
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionID);
        //서버 입장에서 웹브라우저에 응답이 나간것
        response.addCookie(mySessionCookie);
    }

    /**
     * 세션 조회 - 서버에 request 요청
     *
     * * sessionStore<UUID, MemberVO>
     * * sessionCookie<MY_SESSION_ID,UUID>
     * return : 사용자 정보 MemberVO (Object)
     */
    public Object getSession(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        System.out.println("sessionCookie = " + sessionCookie);
        System.out.println("sessionStore.get(sessionCookie.getValue()) = " + sessionStore.get(sessionCookie.getValue()));
        //UUID(sessionCookie의 value)를 통해서 sessionStore에 저장된 MemberVO(value 값) 을 꺼내온다.
        return sessionStore.get(sessionCookie.getValue());
    }
    //리팩토링
    public Cookie findCookie(HttpServletRequest request, String cookieName){
        //성가시게 배열로 반환됨..;
        if(request.getCookies() == null){
            return null;
        }
        //배열을 stream 으로 바꾸자!
        return Arrays.stream(request.getCookies())
                .filter(i -> i.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

    /**
     * 세션 만료
     */
    public void expire(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        //sessionCookie 의 value -> UUID 로 sessionStore의 해당 Key(UUID),Value(MemberVO) 삭제
        if(sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }
}
