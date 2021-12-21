package com.lava.springBlog.springBlog.controller;

import com.lava.springBlog.springBlog.model.MemberVO;
import com.lava.springBlog.springBlog.service.LoginService;
import com.lava.springBlog.springBlog.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model){
        //세션 관리자에 저장된 회원 정보 조회 * object 이기때문에 MemberVO 로 캐스팅
        MemberVO memberVO = (MemberVO)sessionManager.getSession(request);
        System.out.println("memberVO = " + memberVO);
        if(memberVO == null){
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
    @PostMapping("/logout")
    public String homeLogoutV2(HttpServletRequest request){
        sessionManager.expire(request);
        return "redirect:/";
    }


}
