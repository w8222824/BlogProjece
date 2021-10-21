package com.learn.blog.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //跨域配置，不可设置为*，不安全, 前后端分离项目，可能域名不一致
        //本地测试 端口不一致 也算跨域
        //注册允许所有的接口,允许域名 http://localhost:8080 进行访问 这个地址是前端本地的部署地址
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }
}
