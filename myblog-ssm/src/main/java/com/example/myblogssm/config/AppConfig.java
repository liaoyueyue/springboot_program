package com.example.myblogssm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
                .excludePathPatterns("/user/login", "/user/register", "/user/showinfobyid")
                .excludePathPatterns("/article/showinfo", "/article/showinfolistbypage");
    }

    /**
     * 自定义资源映射
     * 由于在浏览器界面上传图片，而 Spring boot 程序是感知不到的，因此需要自定义资源映射
     * addResourceHandler("/upload/**")：表示访问的时候路径上要加上upload，不然访问不到。
     * addResourceLocations("file:upload/")：你图片上传的路径，我的图片上传就在upload文件中。
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:upload/");
    }

}
