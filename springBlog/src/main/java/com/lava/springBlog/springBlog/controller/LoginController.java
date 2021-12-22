package com.lava.springBlog.springBlog.controller;

import com.lava.springBlog.springBlog.model.MemberVO;
import com.lava.springBlog.springBlog.model.SessionConst;
import com.lava.springBlog.springBlog.service.LoginService;
import com.lava.springBlog.springBlog.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/html/Login/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SessionManager sessionManager;

    //로그인 페이지
    @GetMapping("/login")
    public String showPage(){
        return "html/Login/login";
    }

    //로그인 - @ResponseBody 안하면 값이 controller -> js로 안넘어감, 쿠키 전달을 위해 @HttpServletResponse 추가
    //@ResponseBody
    //@RequestMapping(value = "/login", method = {RequestMethod.POST})
    public HashMap<String, String> memberLoginV1(@ModelAttribute MemberVO memberVO, HttpServletResponse response){
        HashMap<String, String> msg = new HashMap<>();
        MemberVO memberData = loginService.memberLogin(memberVO.getUserID(), memberVO.getUserPWD());

        if(memberData == null) {
            msg.put("message", "아이디 또는 비밀번호가 맞지않습니다.");
        } else {
            //쿠키생성
            Cookie idCookie = new Cookie("memberID", String.valueOf(memberData.getId()));
            idCookie.setPath("/");
            response.addCookie(idCookie);
            msg.put("message", "로그인 성공");
        }

        return msg;
    }

    //로그인 - 커스텀 쿠키 적용
    //@ResponseBody
    //@RequestMapping(value = "/login", method = {RequestMethod.POST})
    public HashMap<String, String> memberLoginV2(@ModelAttribute MemberVO memberVO, HttpServletResponse response){
        HashMap<String, String> msg = new HashMap<>();
        MemberVO memberData = loginService.memberLogin(memberVO.getUserID(), memberVO.getUserPWD());

        if(memberData == null) {
            msg.put("message", "아이디 또는 비밀번호가 맞지않습니다.");
        } else {
            //세션관리자를 통해 세션을 생성하고 웹브라우저에 응답, 회원 데이터 보관
            sessionManager.createSession(memberData, response); // -> 세션 생성 : sessionStore<UUID, memberVO> , 쿠키 생성 : Cookie("세션 이름", UUID)

            msg.put("message", "로그인 성공");
        }

        return msg;
    }

    //로그인 - 서블릿 HTTP 세션
    @ResponseBody
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public HashMap<String, String> memberLoginV3(@ModelAttribute MemberVO memberVO, HttpServletRequest request){
        HashMap<String, String> msg = new HashMap<>();
        //전송된 폼데이터로 유저의 전체 데이터를 가져옴
        MemberVO memberData = loginService.memberLogin(memberVO.getUserID(), memberVO.getUserPWD());

        if(memberData == null) {
            msg.put("message", "아이디 또는 비밀번호가 맞지않습니다.");
        } else {
            //세션이 있으면 재사용(true), 없으면 신규 생성(true | false)
            HttpSession session = request.getSession(true);
            //세션에 로그인 회원정보 보관
            session.setAttribute(SessionConst.LOGIN_MEMBER, memberData);
            msg.put("message", "로그인 성공");
        }

        return msg;
    }
}
