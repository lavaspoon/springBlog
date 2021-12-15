package com.lava.springBlog.springBlog.controller;

import com.lava.springBlog.springBlog.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/html/Members/")
public class MembersController {

    //로그인 페이지
    @GetMapping("/addMember")
    public String showPage(){
        return "html/Members/addMember";
    }

    //Data 전달
    @PostMapping("/addMember")
    public Member addMember(@ModelAttribute Member member){
        return member;
    }
}
