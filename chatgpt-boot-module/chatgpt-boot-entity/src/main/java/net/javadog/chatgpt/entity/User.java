package net.javadog.chatgpt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 用户实体
 *
 * @author: hdx
 * @Date: 2023-01-10 11:43
 * @version: 1.0
 **/
@Data
public class User{

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
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
     * 创建时间
     */
    private Date createTime;

}
