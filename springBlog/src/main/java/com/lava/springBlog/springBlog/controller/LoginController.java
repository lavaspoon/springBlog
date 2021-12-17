package com.lava.springBlog.springBlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/html/Login/")
public class LoginController {

    //로그인 페이지
    @GetMapping("/login")
    public String showPage(){
        return "html/Login/login";
    }

}
