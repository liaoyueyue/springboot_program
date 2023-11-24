package com.example.myblogssm.config;

import com.example.myblogssm.common.AppConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
                .excludePathPatterns("/favicon.ico", "/login.html", "/blog_list_comm.html", "/blog_detail.html", "/blog_search.html", "/common/navigation.html")
                .excludePathPatterns("/user/login", "/user/register", "/user/showinfobyid","/user/showinfo")
                .excludePathPatterns("/article/showinfo", "/article/showinfolistbypage","/article/search")
                .excludePathPatterns("/email/sendverificationcodeforregister", "/verify/getcode", "/verify/checkcode")
                .excludePathPatterns("/comment/showinfobyid");
    }

    /**
     * 自定义资源映射
     * 由于在浏览器界面上传图片，而SpringBoot程序是感知不到的，因此需要自定义资源映射
     * addResourceHandler("/upload/**")：表示访问的时候路径上要加上 upload
     * addResourceLocations("classpath:upload/")：classpath:：表示资源位于classpath（类路径）下，通常是在src/main/resources/目录中。
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")// 访问路径
                .addResourceLocations("file:" + AppConstant.IMG_PATH_ABSOLUTE);
    }
}
