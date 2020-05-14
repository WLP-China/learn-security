package com.muqing.init;

import com.muqing.config.ApplicationConfig;
import com.muqing.config.WebConfig;
import com.muqing.config.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 加载 Spring容器,Spring容器初始化类,相当于web.xml,使用了servlet3.0开发则不需要再定义web.xml
 * （此类实现WebApplicationInitializer接口，Spring容器启动时加载WebApplicationInitializer接口的所有实现类）
 */
public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //spring容器。指定rootContext的配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class, WebSecurityConfig.class};
    }

    //servletContext。指定servletContext的配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    //url-mapping
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
/*
<web-app>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/application-context.xml</param-value>
    </context-param>
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
*/