## 会话创建

可以通过以下选项准确控制会话何时创建以及Spring Security如何与之交互：

| 机制       | 描述                                                         |
| ---------- | ------------------------------------------------------------ |
| always     | 如果没有session存在就创建一个                                |
| ifRequired | 如果需要就创建一个Session（默认）登录时                      |
| never      | SpringSecurity 将不会创建Session，但是如果应用中其他地方创建了Session，那么Spring Security将会使用它。 |
| stateless  | SpringSecurity将绝对不会创建Session，也不使用Session         |

通过以下配置方式对该选项进行配置：

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
	http.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
}
```

默认情况下，Spring Security会为每个登录成功的用户会新建一个Session，就是`ifRequired` 。
若选用`never`，则指示Spring Security对登录成功的用户不创建Session了，但若你的应用程序在某地方新建了session，那么Spring Security会用它的。
若使用`stateless`，则说明Spring Security对登录成功的用户不会创建Session了，你的应用程序也不会允许新建session。并且它会暗示不使用cookie，所以每个请求都需要重新进行身份验证。这种无状态架构适用于REST API及其无状态认证机制。

## 会话超时

可以再sevlet容器中设置Session的超时时间：


```properties
#spring boot 配置文件：
server.servlet.session.timeout=3600s #设置Session有效期为3600s
```

session超时之后，可以通过Spring Security 设置跳转的路径

```java
http().sessionManagement()
	.invalidSessionUrl("/login-view?error=INVALID_SESSION")//传入的sessionid无效 跳转的路径
	.maximumSessions(1).expiredUrl("/login-view?error=EXPIRED_SESSION") //单个用户只能创建一个session,session过期 跳转的路径
```

## 安全会话cookie

我们可以使用httpOnly和secure标签来保护我们的会话cookie：
`httpOnly`：如果为true，那么浏览器脚本将无法访问cookie
`secure`：如果为true，则cookie将仅通过HTTPS连接发送

```properties
#spring boot 配置文件：
server.servlet.session.cookie.http-only=true
server.servlet.session.cookie.secure=true
```

