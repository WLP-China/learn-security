package com.muqing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Servlet Context配置
 * 相当于springmvc.xml 对应于DispatcherServlet配置
 */
//由于Spring boot starter自动装配机制，这里无需使用@EnableWebMvc与@ComponentScan，
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //默认Url根路径跳转到/login，此url为spring security提供
        registry.addViewController("/").setViewName("redirect:/login");

//        registry.addViewController("/").setViewName("redirect:/login-view");
//        registry.addViewController("/login-view").setViewName("login");
    }
}
