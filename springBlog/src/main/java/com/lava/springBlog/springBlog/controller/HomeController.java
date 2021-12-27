package com.lava.springBlog.springBlog.controller;

import com.lava.springBlog.springBlog.customAnno.argumentresolver.Login;
import com.lava.springBlog.springBlog.model.MemberVO;
import com.lava.springBlog.springBlog.model.SessionConst;
import com.lava.springBlog.springBlog.service.LoginService;
import com.lava.springBlog.springBlog.session.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(MembersController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private SessionManager sessionManager;

    /**
     * @param userID
     * @param model
     * @return 세션 있으면 loginHome, 세션 없으면 home
     */
    //@GetMapping("/")
    public String homeLoginV1(@CookieValue(name="memberID", required = false) Long userID, Model model){
        if(userID != null){
            MemberVO memberData = loginService.findById(userID);
            model.addAttribute("member", memberData);
            return "/html/Home/loginHome";
        }
        return "/html/Home/home";
    }

    // 로그인 화면 - 커스텀 쿠키 적용
    //@GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model){
        //세션 관리자에 저장된 회원 정보 조회 * object 이기때문에 MemberVO 로 캐스팅
        MemberVO memberVO = (MemberVO)sessionManager.getSession(request);

        if(memberVO == null){
            return "/html/Home/home";
        }
        model.addAttribute("member", memberVO);
        return "/html/Home/loginHome";
    }

    // 로그인 화면 - 서블릿 HTTP 세션
    //@GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model){
        //처음 들어온 사용자는 세션 생성 안되게함
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "/html/Home/home";
        }

        //세션 관리자에 저장된 회원 정보 조회 * object 이기때문에 MemberVO 로 캐스팅
        MemberVO memberVO = (MemberVO)session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(memberVO == null) {
            return "/html/Home/home";
        }
        model.addAttribute("member", memberVO);
        return "/html/Home/loginHome";
    }

    // 로그인 화면 - ArgumentResolver
    @GetMapping("/")
    public String homeLoginV4(@Login MemberVO memberVO, Model model){

        if(memberVO == null) {
            return "/html/Home/home";
        }

        model.addAttribute("member", memberVO);
        return "/html/Home/loginHome";
    }
    //로그아웃
    //@PostMapping("/logout")
    public String homeLogoutV1(HttpServletResponse response){
        expireCookie(response, "memberID");
        return "redirect:/";
    }
    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    //로그아웃 - 커스텀 쿠키 적용
    //@PostMapping("/logout")
    public String homeLogoutV2(HttpServletRequest request){
        sessionManager.expire(request);
        return "redirect:/";
    }

    //로그아웃 - 서블릿 HTTP 세션
    @PostMapping("/logout")
    public String homeLogoutV3(HttpServletRequest request){
        //true로 하면 세션이 없을경우, 새로 생성하기 때문에 false 로 함
        HttpSession session = request.getSession(false);
        //세션 삭제
        if (session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
