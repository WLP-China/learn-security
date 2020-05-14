package com.muqing.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * 认证成功后返回的用户信息(当前登录用户的信息)
 */
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class UserDTO {
    /**
     * session中存放登录用户信息的key
     */
    public static final String SESSION_USER_KEY = "_user";

    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
    /**
     * 用户权限
     */
    private Set<String> authorities;
}
