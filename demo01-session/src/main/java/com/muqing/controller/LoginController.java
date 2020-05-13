package com.muqing.controller;

import com.muqing.model.AuthenticationRequest;
import com.muqing.model.UserDTO;
import com.muqing.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    AuthenticationService authenticationService;

    /**
     * 用户登录
     * @param authenticationRequest 登录请求
     * @param session http会话
     * @return
     */
    @PostMapping(value = "/login", produces = "text/plain;charset=utf-8")//produces属性可以设置返回数据的类型以及编码-> 解决中文乱码
    public String login(AuthenticationRequest authenticationRequest, HttpSession session) {
        UserDTO userDTO = authenticationService.authentication(authenticationRequest);
        //存入session
        session.setAttribute(UserDTO.SESSION_USER_KEY, userDTO);
        return userDTO.getUsername() + "登录成功";
    }

    /**
     * 用户登出
     * @param session http会话
     * @return
     */
    @GetMapping(value = "/logout", produces = "text/plain;charset=utf-8")
    public String logout(HttpSession session) {
        session.invalidate();
        return "退出成功";
    }

    //测试资源1
    @GetMapping(value = "/r/r1", produces = "text/plain;charset=utf-8")
    public String r1(HttpSession session) {
        String fullname = null;
        Object userObiect = session.getAttribute(UserDTO.SESSION_USER_KEY);
        if (userObiect == null) {
            fullname = "匿名";
        } else {
            UserDTO userDTO = (UserDTO) userObiect;
            fullname = userDTO.getFullname();
        }
        return fullname + " 访问资源r1";
    }

//    @GetMapping(value = "/r/r2", produces = {"text/plain;charset=UTF-8"})
//    public String r2(HttpSession session) {
//        String fullname = null;
//        Object userObj = session.getAttribute(UserDto.SESSION_USER_KEY);
//        if (userObj != null) {
//            fullname = ((UserDto) userObj).getFullname();
//        } else {
//            fullname = "匿名";
//        }
//        return fullname + " 访问资源2";
//    }
}
