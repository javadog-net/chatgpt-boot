package net.javadog.chatgpt.common.exception;

import lombok.Getter;
import lombok.Setter;
import net.javadog.chatgpt.common.enums.AbstractBaseExceptionEnum;

/**
 * @Description: 异常类
 * @Author: hdx
 * @Date: 2022/1/26 10:47
 * @Version: 1.0
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -9149947871782927397L;

    @Getter
    @Setter
    private Integer errorCode;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(AbstractBaseExceptionEnum abstractBaseExceptionEnum) {
        super(abstractBaseExceptionEnum.getResultMsg());
        this.errorCode = abstractBaseExceptionEnum.getResultCode();
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
