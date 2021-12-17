package com.lava.springBlog.springBlog.service;

import com.lava.springBlog.springBlog.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public int checkID(String userID) {
        int count = memberMapper.checkID(userID);
        return count;
    }
}
