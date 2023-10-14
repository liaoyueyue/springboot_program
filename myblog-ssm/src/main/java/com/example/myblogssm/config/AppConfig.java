package com.example.myblogssm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-11
 * Time: 17:58
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/editor.md/**", "/images/**", "/js/**")
                .excludePathPatterns("/favicon.ico", "/login.html", "/blog_list_comm.html", "/blog_detail.html")
                .excludePathPatterns("/user/login", "/user/register");
    }
}
