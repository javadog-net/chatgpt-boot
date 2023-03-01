package net.javadog.chatgpt.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Description: 登录信息Vo
 * @Author: hdx
 * @Date: 2023/02/29 15:38
 * @Version: 1.0
 */
@Data
@ApiModel("登录信息")
public class LoginRequest implements Serializable {

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "请输入手机号")
    @Pattern(regexp = "^1[23456789]\\d{9}$", message = "手机号格式错误！")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "请输入密码")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{6,16}$", message = "请输入6-16位密码！")
    private String password;
}
