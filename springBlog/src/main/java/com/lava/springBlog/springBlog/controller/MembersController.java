package com.lava.springBlog.springBlog.controller;

import com.lava.springBlog.springBlog.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 *  validate
 *  JSP 검증 O
 *  Spring 검증 O
 */
@Controller
@RequestMapping("/html/Members/")
public class MembersController {
    private static final Logger logger = LoggerFactory.getLogger(MembersController.class);
    //로그인 페이지
    @GetMapping("/addMember")
    public String showPage(Model model){
        //타임리프로 값을 불러오기 때문에 빈값을 넘겨줘야함
        //model.addAttribute("member", new Member());
        return "html/Members/addMember";
    }

    //Data 전달 - v1
    @ResponseBody
    @RequestMapping(value = {"/addMember"}, method = {RequestMethod.POST})
    public HashMap<String, String> addMember(@ModelAttribute Member member, Model model){

        HashMap<String, String> msg = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        //valid 검증
        if(!StringUtils.hasText(member.getUserID())){
            errors.put("ID_Error", "ID는 필수 값 입니다.");
        }
        if(!StringUtils.hasText(member.getUserPWD())){
            errors.put("PWD_Error", "패스워드는 필수 값 입니다.");
        }
        if(!StringUtils.hasText(member.getUserPWD_Check())){
            errors.put("PWD_Check_Error", "패스워드는 필수 값 입니다.");
        }

        if(!errors.isEmpty()){
            logger.info("errors = {}", errors);
            msg.put("message", "회원가입 실패");
            return msg;
        }

        msg.put("message", "회원가입 성공");
        return msg;
    }
}
