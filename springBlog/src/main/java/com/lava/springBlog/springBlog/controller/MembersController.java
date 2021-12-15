package com.lava.springBlog.springBlog.controller;

import com.lava.springBlog.springBlog.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/html/Members/")
public class MembersController {

    private static final Logger logger = LoggerFactory.getLogger(MembersController.class);
    //로그인 페이지
    @GetMapping("/addMember")
    public String showPage(){
        return "html/Members/addMember";
    }

    //Data 전달
    @RequestMapping(value = {"/addMember"}, method = {RequestMethod.POST})
    public void addMember(@ModelAttribute Member member){
        logger.info("=========> 회원가입 전송된 데이터 <========");
        logger.info("getUserID = {}", member.getUserID());
        logger.info("getUserPWD = {}", member.getUserPWD());
        logger.info("getUserPWD_Check = {}", member.getUserPWD_Check());
        logger.info("getUserName = {}", member.getUserName());
        logger.info("getUserPhone = {}", member.getUserPhone());
        logger.info("=====================================");
    }
}
