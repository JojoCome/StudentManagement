package com.hotelmanage.backend.controller;

import com.hotelmanage.backend.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description:登录接口控制器,处理前端发来的登录请求
 */
@RestController
@RequestMapping("/api")
public class LoginController {
    private final AdminService adminService = new AdminService();

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        boolean success = adminService.login(username, password);
        if (success) {
            return Map.of("success", true, "message", "登录成功");
        } else {
            return Map.of("success", false, "message", "登录失败");
        }
    }

    /*
| 内容                        | 说明                      |
| ------------------------- | ----------------------- |
| `@RestController`         | 标记为 REST 风格的控制器，返回 JSON |
| `@RequestMapping("/api")` | 所有接口都以 `/api` 开头        |
| `@PostMapping("/login")`  | 接收 POST 登录请求            |
| `@RequestBody`            | 接收 JSON 格式的请求体          |
::接受HTTP请求体中的JSON数据,并把它自动转换为java对象或者map
::前端发送的JSON格式的body,Spring boot使用httpMessageConverter将JSON转换为java对象
::将JSON转为java对象并注入到方法参数中
::@RequestBody 看到Json格式数据会自动调用jackson的ObjectMapper.readValue()
|  `Map<String, Object>`  | 直接构造 JSON 响应（简单好用）      |
*/

}
