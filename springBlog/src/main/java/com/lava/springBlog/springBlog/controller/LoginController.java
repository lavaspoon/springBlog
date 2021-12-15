package com.lava.springBlog.springBlog.controller;

import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/html/Login/")
public class LoginController {

    //로그인 페이지
    @GetMapping("/login")
    public String showPage(){
        return "html/Login/login";
    }
}
