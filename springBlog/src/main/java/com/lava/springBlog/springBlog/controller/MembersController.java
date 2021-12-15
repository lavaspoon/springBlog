package com.lava.springBlog.springBlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/html/Members/")
public class MembersController {

    //로그인 페이지
    @GetMapping("/addMember")
    public String showPage(){
        return "html/Members/addMember";
    }
}
