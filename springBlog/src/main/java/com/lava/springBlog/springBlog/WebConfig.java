package com.lava.springBlog.springBlog;

import com.lava.springBlog.springBlog.filter.LoginFiler;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfig {
    //스프링부트는 WAS를 내장하기 때문에 서버실행때 아래 메서드를 실행함
    @Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginFiler());
        //필터체인 순서
        filterFilterRegistrationBean.setOrder(1);
        //모든 URL에 다 적용
        filterFilterRegistrationBean.addUrlPatterns("/*");

        return filterFilterRegistrationBean;
    }
}
