package org.example.myojssm.config;

import org.example.myojssm.config.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-24
 * Time: 14:22
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${local-directory}")
    private String LOCAL_DIRECTORY;

    @Autowired
    private LoginInterceptor LoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(LoginInterceptor)
                .addPathPatterns("/**") // 拦截所有接口
                .excludePathPatterns("/user/login", "/user/register", "/index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/local/**").addResourceLocations("file:" + LOCAL_DIRECTORY);
    }
}
