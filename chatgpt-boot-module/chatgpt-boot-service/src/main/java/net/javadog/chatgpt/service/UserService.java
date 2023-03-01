package net.javadog.chatgpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.javadog.chatgpt.dto.request.LoginRequest;
import net.javadog.chatgpt.dto.request.RegisterRequest;
import net.javadog.chatgpt.dto.response.UserResponse;
import net.javadog.chatgpt.entity.User;

/**
 * 用户接口
 *
 * @author: hdx
 * @Date: 2023-01-10 11:53
 * @version: 1.0
 **/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param registerRequest
     * @return Boolean
     */
    Boolean register(RegisterRequest registerRequest);

    /**
     * 用户登录
     *
     * @param loginRequest
     * @return UserResponse
     */
    UserResponse login(LoginRequest loginRequest);

}
