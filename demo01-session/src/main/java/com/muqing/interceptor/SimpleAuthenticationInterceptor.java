package com.muqing.interceptor;

import com.muqing.model.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 授权拦截器
 */
@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    //请求拦截方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在这个方法中校验用户请求的url是否在用户的权限范围内

        //读取会话信息
        Object object = request.getSession().getAttribute(UserDTO.SESSION_USER_KEY);
        if (object == null) {
            writeContent(response, "请登录");//没有认证，提示登录
        }else {
            UserDTO userDTO = (UserDTO) object;
            String requestURI = request.getRequestURI();//请求的url
            if (userDTO.getAuthorities().contains("p1") && requestURI.contains("/r/r1")) {
                return true;
            }
            if (userDTO.getAuthorities().contains("p2") && requestURI.contains("/r/r2")) {
                return true;
            }
        }
        writeContent(response, "没有权限，拒绝访问");
        return false;
    }

    //响应信息给客户端
    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
    }
}
