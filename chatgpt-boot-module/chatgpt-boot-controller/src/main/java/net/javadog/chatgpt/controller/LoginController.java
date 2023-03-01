package net.javadog.chatgpt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.chatgpt.common.response.ResponseData;
import net.javadog.chatgpt.dto.request.LoginRequest;
import net.javadog.chatgpt.dto.response.UserResponse;
import net.javadog.chatgpt.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Description: 用户控制器
 * @Author: hdx
 * @Date: 2022/1/13 16:39
 * @Version: 1.0
 */

@RestController
@Api(tags = "登录控制器")
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户登录", notes = "登录--不进行拦截")
    @PostMapping
    public ResponseData login(@RequestBody @Valid LoginRequest loginRequest) {
        UserResponse userResponse = userService.login(loginRequest);
        return ResponseData.success(userResponse);
    }
}
