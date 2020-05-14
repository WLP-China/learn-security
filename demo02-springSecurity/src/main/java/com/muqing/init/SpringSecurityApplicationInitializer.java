package com.muqing.init;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Spring Security初始化
 */
public class SpringSecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    public SpringSecurityApplicationInitializer() {
        /*
         * 若当前环境没有使用Spring或Spring MVC，则需要将 WebSecurityConfig(Spring Security配置类) 传入超类，以确保获取配置，并创建spring context。
         *
         * 若当前环境已经使用spring，我们应该在现有的springContext中注册Spring Security(SpringApplicationInitializer中已经将WebSecurityConfig加载至rootcontext)，此方法可以什么都不做。
         * */
        //super(WebSecurityConfig.class);
    }
}