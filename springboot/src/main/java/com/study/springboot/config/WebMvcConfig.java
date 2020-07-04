package com.study.springboot.config;

import com.study.springboot.intercept.LoginHandlerIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Cdu
 * @discription:
 * @create 2020-07-04 23:43
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:login.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        拦截所有请求，除了首页、登录请求、资源等
        registry.addInterceptor(new LoginHandlerIntercept()).addPathPatterns("/**")
                .excludePathPatterns("/","login.html","/user/login","/css/**","/js/**","/img/**");
    }
}