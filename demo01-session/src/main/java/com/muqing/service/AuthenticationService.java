package com.muqing.service;

import com.muqing.model.AuthenticationRequest;
import com.muqing.model.UserDTO;

/**
 * 认证服务
 */
public interface AuthenticationService {
    /**
     * 用户认证
     *
     * @param authenticationRequest 用户认证请求，账号和密码
     * @return 认证成功的用户信息
     */
    UserDTO authentication(AuthenticationRequest authenticationRequest);
}
