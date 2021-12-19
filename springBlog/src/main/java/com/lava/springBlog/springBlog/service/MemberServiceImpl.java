package com.lava.springBlog.springBlog.service;

import com.lava.springBlog.springBlog.mapper.MemberMapper;
import com.lava.springBlog.springBlog.model.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public int checkID(String userID) {
        int count = memberMapper.checkID(userID);
        return count;
    }

    @Override
    public void memberJoin(MemberVO memberVO) {
        memberMapper.insertMember(memberVO);
    }
}
