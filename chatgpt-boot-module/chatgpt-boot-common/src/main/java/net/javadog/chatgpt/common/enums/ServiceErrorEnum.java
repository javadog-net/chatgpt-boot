package net.javadog.chatgpt.common.enums;

import lombok.Getter;

/**
 * @Description: 业务枚举类
 * @Author: hdx
 * @Date: 2022/1/30 9:12
 * @Version: 1.0
 */
@Getter
public enum ServiceErrorEnum implements AbstractBaseExceptionEnum {

    // 用户错误相关
    USER_IS_EXIT(1001, "用户已存在!"),
    USERNAME_OR_PASSWORD_ERROR(1002, "账号或密码不正确!"),
    CONFIRMATION_PASSWORD_ERROR(1003, "确认密码不一致!"),
    ;

    /**
     * 错误码
     */
    private final Integer resultCode;

    /**
     * 错误描述
     */
    private final String resultMsg;

    ServiceErrorEnum(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
