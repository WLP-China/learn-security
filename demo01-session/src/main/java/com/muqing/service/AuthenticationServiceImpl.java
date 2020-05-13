package com.muqing.service;

import com.muqing.model.AuthenticationRequest;
import com.muqing.model.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private Map<String, UserDTO> userMap = new HashMap<>();//用户信息

    {
//        userMap.put("zhangsan",new UserDTO("1010","zhangsan","123","张三","123123"));
//        userMap.put("lisi",new UserDTO("1011","lisi","456","李四","456456"));

        Set<String> authorities1 = new HashSet<>();
        authorities1.add("p1");//这个p1我们人为让它和/r/r1对应
        Set<String> authorities2 = new HashSet<>();
        authorities2.add("p2");//这个p2我们人为让它和/r/r2对应
        userMap.put("zhangsan", new UserDTO("1010", "zhangsan", "123", "张三", "133443", authorities1));
        userMap.put("lisi", new UserDTO("1011", "lisi", "456", "李四", "144553", authorities2));
    }

    /**
     * 用户认证，校验用户身份信息是否合法
     *
     * @param authenticationRequest 用户认证请求，账号和密码
     * @return 认证成功的用户信息
     */
    @Override
    public UserDTO authentication(AuthenticationRequest authenticationRequest) {
        //校验参数是否为空
        if (authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())) {
            throw new RuntimeException("账号和密码为空");
        }
        //根据账号去查询数据库,这里测试程序采用模拟方法
        UserDTO user = getUserDTO(authenticationRequest.getUsername());
        //判断用户是否为空
        if (user == null) {
            throw new RuntimeException("查询不到该用户");
        }
        //校验密码
        if (!authenticationRequest.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("账号或密码错误");
        }
        //认证通过，返回用户身份信息
        return user;
    }

    //根据账号查询用户信息
    private UserDTO getUserDTO(String userName) {
        return userMap.get(userName);
    }
}
