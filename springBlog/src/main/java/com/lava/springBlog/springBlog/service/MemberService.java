package com.lava.springBlog.springBlog.service;

import org.springframework.stereotype.Service;
//@Service
public interface MemberService {
    // 아이디 중복확인
    public int checkID(String userID);
}
