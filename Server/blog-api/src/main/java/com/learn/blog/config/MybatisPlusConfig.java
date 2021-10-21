package com.learn.blog.config;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
@Slf4j
@Configuration
//相关的接口都写到mapper层
@MapperScan("com.learn.blog.dao.mapper")
public class MybatisPlusConfig
{
    //分页插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        log.info("测试分页");
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
