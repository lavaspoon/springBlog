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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(MembersController.class);

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String homeLogin(@CookieValue(name="memberID", required = false) Long userID, Model model){
        if(userID != null){
            MemberVO memberData = loginService.findById(userID);
            return "/html/Home/loginHome";
        }
        return "/html/Home/home";
    }
}
