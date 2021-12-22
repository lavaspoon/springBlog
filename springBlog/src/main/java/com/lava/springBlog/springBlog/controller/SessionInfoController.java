package com.lava.springBlog.springBlog.controller;

import com.lava.springBlog.springBlog.model.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Enumeration;

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return "세션이 없습니다.";
        }
        //세션이 있으면
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name= {}, value= {}", name, session.getAttribute(name)));

        log.info("session name = {}", session.getId());
        log.info("session getMaxInactiveInterval = {}", session.getMaxInactiveInterval());
        log.info("session getCreationTime = {}", new Date(session.getCreationTime()));
        log.info("session getLastAccessedTime = {}", new Date(session.getLastAccessedTime()));
        log.info("session isNew = {}", session.isNew());

        return "세션출력";

    }
}
