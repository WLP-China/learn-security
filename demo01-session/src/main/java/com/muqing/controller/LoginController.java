package com.muqing.controller;

import com.muqing.model.AuthenticationRequest;
import com.muqing.model.UserDTO;
import com.muqing.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(value = "/login", produces = "text/plain;charset=utf-8")//produces = text/plain ->返回类型为文本类型
    public String login(AuthenticationRequest authenticationRequest, HttpSession session) {
        UserDTO userDto = authenticationService.authentication(authenticationRequest);
        //存入session
//        session.setAttribute(UserDto.SESSION_USER_KEY, userDto);
        return userDto.getUsername() + "登录成功";
    }
//
//    @GetMapping(value = "/logout", produces = {"text/plain;charset=UTF-8"})
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "退出成功";
//    }
//
//    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=UTF-8"})
//    public String r1(HttpSession session) {
//        String fullname = null;
//        Object object = session.getAttribute(UserDto.SESSION_USER_KEY);
//        if (object == null) {
//            fullname = "匿名";
//        } else {
//            UserDto userDto = (UserDto) object;
//            fullname = userDto.getFullname();
//        }
//        return fullname + "访问资源r1";
//    }
//
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
