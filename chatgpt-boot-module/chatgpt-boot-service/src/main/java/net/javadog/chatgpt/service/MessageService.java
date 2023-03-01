package net.javadog.chatgpt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import net.javadog.chatgpt.dto.request.MessageRequest;
import net.javadog.chatgpt.dto.response.MessageResponse;
import net.javadog.chatgpt.entity.Message;

/**
 * @Description: 好友消息接口
 * @Author: hdx
 * @Date: 2022/1/13 16:27
 * @Version: 1.0
 */
public interface MessageService extends IService<Message> {

    /**
     * 消息保存
     *
     * @param messageRequest
     * @return MessageResponse
     */
    MessageResponse add(MessageRequest messageRequest);

    /**
     * 消息分页列表
     *
     * @param messageRequest
     * @param current
     * @param size
     * @return IPage<MessageResponse>
     */
    IPage<MessageResponse> page(MessageRequest messageRequest, Integer current, Integer size);

}
