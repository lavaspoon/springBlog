package com.lava.springBlog.springBlog.mapper;

import com.lava.springBlog.springBlog.model.MemberVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {
    //회원가입 Insert
    @Insert("INSERT INTO MEMBERS VALUES(members_seq.nextval, #{memberVO.userID}, #{memberVO.userPWD}, #{memberVO.userName}, #{memberVO.userPhone})")
    int insertMember(@Param("memberVO") MemberVO memberVO);
    //중복체크 SELECT COUNT(id) FROM USER WHERE id= #{id}
    @Select("SELECT COUNT(#{userID}) FROM MEMBERS WHERE userID = #{userID}")
    int checkID(@Param("userID") String userID);

}
