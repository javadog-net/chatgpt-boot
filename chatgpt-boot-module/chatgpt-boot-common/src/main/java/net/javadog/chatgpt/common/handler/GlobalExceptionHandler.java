package net.javadog.chatgpt.common.handler;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import net.javadog.chatgpt.common.enums.HttpCodeEnum;
import net.javadog.chatgpt.common.exception.ServiceException;
import net.javadog.chatgpt.common.response.ResponseData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 全局异常
 * @Author: hdx
 * @Date: 2022/1/13 15:46
 * @Version: 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseData<String> serviceException(ServiceException ex) {
        log.error(Throwables.getStackTraceAsString(ex));
        return ResponseData.error(ex.getErrorCode(), ex.getMessage());
    }

    /**
     * 其他异常处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseData<String> exception(Exception ex) {
        String errMsg = Throwables.getStackTraceAsString(ex);
        log.error(errMsg);
        return ResponseData.error(HttpCodeEnum.INTERNAL_SERVER_ERROR).setMessage(ex.getCause().getMessage());
    }

}
