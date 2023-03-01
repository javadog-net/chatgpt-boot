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
public class Message {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 消息发送方
     */
    private Long fromUserId;

    /**
     * 消息接收方
     */
    private Long toUserId;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createTime;

}
