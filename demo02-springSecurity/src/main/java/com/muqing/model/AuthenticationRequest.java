package com.muqing.model;

import lombok.Data;

/**
 * 认证请求参数
 */
@Data
public class AuthenticationRequest {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
