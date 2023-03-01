package net.javadog.chatgpt.common.enums;

/**
 * @Description: 自定义基础接口类
 * @Author: hdx
 * @Date: 2022/1/21 16:21
 * @Version: 1.0
 */
public interface AbstractBaseExceptionEnum {
    /**
     * 获取结果错误码
     *
     * @param
     * @return java.lang.Integer
     */
    Integer getResultCode();

    /**
     * 获取错误描述
     *
     * @param
     * @return java.lang.String
     */
    String getResultMsg();
}
