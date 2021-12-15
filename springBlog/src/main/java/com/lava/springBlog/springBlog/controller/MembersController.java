package com.lava.springBlog.springBlog.controller;

import com.lava.springBlog.springBlog.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/html/Members/")
public class MembersController {

    private static final Logger logger = LoggerFactory.getLogger(MembersController.class);
    //로그인 페이지
    @GetMapping("/addMember")
    public String showPage(Model model){
        //타임리프로 값을 불러오기 때문에 빈값을 넘겨줘야함
        model.addAttribute("member", new Member());
        return "html/Members/addMember";
    }

    //Data 전달
    @RequestMapping(value = {"/addMember"}, method = {RequestMethod.POST})
    public String addMember(@ModelAttribute Member member, Model model){
        logger.info("=========> 회원가입 전송된 데이터 <========");
        logger.info("getUserID = {}", member.getUserID());
        logger.info("getUserPWD = {}", member.getUserPWD());
        logger.info("getUserPWD_Check = {}", member.getUserPWD_Check());
        logger.info("getUserName = {}", member.getUserName());
        logger.info("getUserPhone = {}", member.getUserPhone());
        logger.info("=====================================");

        model.addAttribute("member", member);
        return "html/Members/addMember";
    }
}
