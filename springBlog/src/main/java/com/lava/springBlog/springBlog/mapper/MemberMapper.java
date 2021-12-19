package com.lava.springBlog.springBlog.mapper;

import com.lava.springBlog.springBlog.model.MemberVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    //회원가입 Insert
    @Insert("INSERT INTO MEMBERS VALUES(members_seq.nextval, #{memberVO.userID}, #{memberVO.userPWD}, #{memberVO.userName}, #{memberVO.userPhone})")
    int insertMember(@Param("memberVO") MemberVO memberVO);

    //중복체크 select count(userid) from members where userid = 'lava';
    @Select("SELECT COUNT(#{userID}) FROM MEMBERS WHERE userID = #{userID}")
    int checkID(@Param("userID") String userID);

    //로그인 select * from members where userid = 'lava';
    //값이 null 일때를 대비해서 옵셔널 처리
    @Select("SELECT * FROM MEMBERS WHERE userID = #{userID}")
    Optional<MemberVO> memberLogin(@Param("userID") String userID);

    //id값으로 멤버 객체반환(세션로그인에서 활용) select * from members where id = 'id값';
    @Select("SELECT * FROM MEMBERS WHERE id = #{id}")
    MemberVO findById(@Param("id") Long id);
}
