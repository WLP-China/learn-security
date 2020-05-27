package com.muqing.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * spring security登陆处理
 * Create by iFun on 2020/05/26
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

    {
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return manager.loadUserByUsername(username);
    }
}
