package com.muqing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * SpringSecurity安全配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //配置用户信息服务（查询用户信息）
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
//        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
//        return manager;
//    }

    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    //配置安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//屏蔽CSRF控制.(spring security为防止CSRF（Cross-site request forgery跨站请求伪造）的发生，限制了除了get以外的大多数方法)

        http.authorizeRequests()
//                .antMatchers("/r/r1").hasAuthority("p1")//访问/r/r1资源的 url需要拥有p1权限
//                .antMatchers("/r/r2").hasAuthority("p2")

                .antMatchers("/r/**").authenticated()//url匹配/r/**的资源，经过认证后才能访问
                .anyRequest().permitAll()//其他url完全开放
                .and()

                .formLogin()//允许表单登录
                .loginPage("/login-view")//指定登录页面,spring security以重定向方式跳转到/login-view
                .loginProcessingUrl("/login")//指定登录处理的URL，也就是用户名、密码表单提交的目的路径
                .successForwardUrl("/login-success")//指定登录成功后的跳转URL
                .permitAll();//我们必须允许所有用户访问我们的登录页（例如为验证的用户），这个 formLogin().permitAll() 方法允许任意用户访问基于表单登录的所有的URL
    }
}
