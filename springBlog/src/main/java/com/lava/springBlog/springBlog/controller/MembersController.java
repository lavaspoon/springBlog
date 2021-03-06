package com.lava.springBlog.springBlog.controller;

import com.lava.springBlog.springBlog.mapper.MemberMapper;
import com.lava.springBlog.springBlog.model.MemberVO;
import com.lava.springBlog.springBlog.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MemberService memberService;

    private static final Logger logger = LoggerFactory.getLogger(MembersController.class);
    //로그인 페이지
    @GetMapping("/addMember")
    public String addMemberPage(Model model){
        //타임리프로 값을 불러오기 때문에 빈값을 넘겨줘야함
        //model.addAttribute("member", new Member());
        return "html/Members/addMember";
    }
    //회원가입 전송 (Model 지워도 됨)
    @ResponseBody
    @RequestMapping(value = {"/addMember"}, method = {RequestMethod.POST})
    public HashMap<String, String> addMember(@ModelAttribute MemberVO memberVO, Model model){

        HashMap<String, String> msg = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        //valid 검증
        if(!StringUtils.hasText(memberVO.getUserID())){
            errors.put("ID_Error", "ID는 필수 값 입니다.");
        }
        if(!StringUtils.hasText(memberVO.getUserPWD())){
            errors.put("PWD_Error", "패스워드는 필수 값 입니다.");
        }
        if(!StringUtils.hasText(memberVO.getUserPWD_Check())){
            errors.put("PWD_Check_Error", "패스워드는 필수 값 입니다.");
        }

        if(!errors.isEmpty()){
            logger.info("errors = {}", errors);
            msg.put("message", "회원가입 실패");
            return msg;
        }
        //ID 중복검사(스프링쪽 검사)
        int count = memberService.checkID(memberVO.getUserID());
        logger.info("회원가입 전송버튼 중복검사 count = {}", count);
        if(count != 0) {
            msg.put("message", "아이디가 중복입니다.");
            return msg;
        }

        msg.put("message", "회원가입 성공");
        //memberMapper.insertMember(memberVO);
        memberService.memberJoin(memberVO);
        return msg;
    }

    @ResponseBody
    @PostMapping("/ckeckID")
    public int checkID(@RequestParam("userID") String userID){
        //int count = memberMapper.checkID(userID);
        int count = memberService.checkID(userID);
        logger.info("실시간 중복검사 count = {}", count);
        return count;
    }


    //회원 관리
    //로그인 페이지
    @GetMapping("/manageMember")
    public String manageMemberPage(Model model){
        //타임리프로 값을 불러오기 때문에 빈값을 넘겨줘야함
        //model.addAttribute("member", new Member());
        return "html/Members/manageMember";
    }
}
