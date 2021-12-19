package com.lava.springBlog.springBlog.controller;

import com.lava.springBlog.springBlog.model.MemberVO;
import com.lava.springBlog.springBlog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/html/Login/")
public class LoginController {

    @Autowired
    private LoginService loginService;
    //로그인 페이지
    @GetMapping("/login")
    public String showPage(){
        return "html/Login/login";
    }

    //로그인 - ResponseBody 안하면 값이 cont -> js로 안넘어감
    @ResponseBody
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public HashMap<String, String> memberLogin(@ModelAttribute MemberVO memberVO){
        HashMap<String, String> msg = new HashMap<>();
        MemberVO memberData = loginService.memberLogin(memberVO.getUserID(), memberVO.getUserPWD());

        if(memberData == null) {
            msg.put("message", "아이디 또는 비밀번호가 맞지않습니다.");
        } else {
            msg.put("message", "로그인 성공");
        }

        return msg;
    }
}
