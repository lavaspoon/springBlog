package com.lava.springBlog.springBlog.controller;

import com.lava.springBlog.springBlog.model.MemberVO;
import com.lava.springBlog.springBlog.service.LoginService;
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
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(MembersController.class);

    @Autowired
    private LoginService loginService;

    /**
     * @param userID
     * @param model
     * @return 세션 있으면 loginHome, 세션 없으면 home
     */
    @GetMapping("/")
    public String homeLogin(@CookieValue(name="memberID", required = false) Long userID, Model model){
        if(userID != null){
            MemberVO memberData = loginService.findById(userID);
            model.addAttribute("member", memberData);
            return "/html/Home/loginHome";
        }
        return "/html/Home/home";
    }

    //로그아웃
    @PostMapping("/logout")
    public String homeLogout(HttpServletResponse response){
        expireCookie(response, "memberID");
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
