package com.lava.springBlog.springBlog;

import com.lava.springBlog.springBlog.customAnno.argumentresolver.LoginMemberArgumentResolver;
import com.lava.springBlog.springBlog.filter.LoginCheckFilter;
import com.lava.springBlog.springBlog.filter.LoginFiler;
import com.lava.springBlog.springBlog.interceptor.LogInterceptor;
import com.lava.springBlog.springBlog.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //스프링부트는 WAS를 내장하기 때문에 서버실행때 아래 메서드를 실행함

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    /**
 * ArgumentResolver
 /**


 /**
 * 인터셉터로 로그, 로그인 인증 체크
 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/javascript/**", "/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/html/Login/login", "/logout", "/html/Members/addMember",
                        "/javascript/**", "/css/**", "/*.ico", "/error");
    }


/**
 * 필터로 로그, 로그인 인증 체크
 */
    //@Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginFiler());
        //필터체인 순서
        filterFilterRegistrationBean.setOrder(1);
        //모든 URL에 다 적용
        filterFilterRegistrationBean.addUrlPatterns("/*");

        return filterFilterRegistrationBean;
    }

    //@Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new
                FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}
