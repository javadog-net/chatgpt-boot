package net.javadog.chatgpt.dto.response;

import lombok.Data;

import java.util.Date;

/**
 * 用户Response
 *
 * @author: hdx
 * @Date: 2023-01-10 11:43
 * @version: 1.0
 **/
@Data
public class UserResponse {

    /**
     * id
     */
    private Long id;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * accessToken
     */
    private String accessToken;

    /**
     * 创建时间
     */
    private Date createTime;

}
