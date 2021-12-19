package com.lava.springBlog.springBlog.service;

import com.lava.springBlog.springBlog.model.MemberVO;
import org.springframework.stereotype.Service;
//@Service
public interface MemberService {
    // 아이디 중복확인
    public int checkID(String userID);
    // 회원가입
    public  void memberJoin(MemberVO memberVO);
}
