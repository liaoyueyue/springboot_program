package com.example.danmudemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-06
 * Time: 10:51
 */
@Configuration
public class FilePathConfig implements WebMvcConfigurer {

    @Value("${DanmuPlayer.videoPath}")
    private String videoPath;

    @Value("${DanmuPlayer.imagePath}")
    private String imagePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/video/**").addResourceLocations("file:" + videoPath);
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + imagePath);
    }
}
