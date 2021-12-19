package com.lava.springBlog.springBlog.service;

import com.lava.springBlog.springBlog.mapper.MemberMapper;
import com.lava.springBlog.springBlog.model.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 클래스로 인터페이스 사용안하고 진행
 */
@Service
public class LoginService {

    @Autowired
    private MemberMapper memberMapper;

    /**
     * @return 이 null 이면 실패
     */
    public MemberVO memberLogin(String userID, String userPWD) {
        //Optional<MemberVO> findMemberOptional = memberMapper.memberLogin(userID);

        //1번 방법(초보)
//        MemberVO memberVO = findMemberOptional.get();
//        if(memberVO.getUserPWD().equals(userPWD)) {
//            return memberVO;
//        } else {
//            return null;
//        }
        //2번 방법(고급) -> 위의 인스턴스 생성한것과 아래 문장을 합칠수도 있음
//        return findMemberOptional.filter(m -> m.getUserPWD().equals(userPWD))
//                .orElse(null);

        //3번 방법(고급 응용)
        return memberMapper.memberLogin(userID)
                .filter(m -> m.getUserPWD().equals(userPWD))
                .orElse(null);
    }
}
