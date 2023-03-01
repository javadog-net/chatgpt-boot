package net.javadog.chatgpt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.javadog.chatgpt.common.enums.ServiceErrorEnum;
import net.javadog.chatgpt.common.exception.ServiceException;
import net.javadog.chatgpt.common.response.ResponseData;
import net.javadog.chatgpt.dto.request.RegisterRequest;
import net.javadog.chatgpt.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Description: 注册控制器
 * @Author: hdx
 * @Date: 2022/1/29 14:57
 * @Version: 1.0
 */
@Api(tags = "注册控制器")
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户注册", notes = "用户自主注册")
    @PostMapping
    public ResponseData register(@RequestBody @Valid RegisterRequest registerRequest) {
        // 校验两次密码是否一致
        if (!registerRequest.getPassword().equals(registerRequest.getPasswordVerify())) {
            throw new ServiceException(ServiceErrorEnum.CONFIRMATION_PASSWORD_ERROR);
        }
        userService.register(registerRequest);
        return ResponseData.success();
    }
}
