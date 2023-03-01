package net.javadog.chatgpt.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import net.javadog.chatgpt.dto.request.MessageRequest;
import net.javadog.chatgpt.dto.response.MessageResponse;
import net.javadog.chatgpt.entity.Message;
import net.javadog.chatgpt.mapper.MessageMapper;
import net.javadog.chatgpt.service.MessageService;
import net.javadog.chatgpt.shiro.util.SubjectUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 信息接口实现类
 * @Author: hdx
 * @Date: 2023/1/13 16:32
 * @Version: 1.0
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {


    @Value("${openai.token}")
    private String OPENAPI_TOKEN;

    @Value("${openai.timeout}")
    private Integer TIMEOUT;

    private final static String MODEL = "text-davinci-003";

    private final static String PROMPT = "Human: %s";

    private final static Double TEMPERATURE = 0.9;

    private final static Double TOPP = 1.0;

    private final static Double FREQUENCYPENALTY = 0.0;

    private final static Double PRESENCEPENALTY = 0.6;


    @Override
    public MessageResponse add(MessageRequest messageRequest) {
        Long userId = SubjectUtil.getUserId();
        Message message = new Message();
        BeanUtil.copyProperties(messageRequest, message);
        message.setFromUserId(userId);
        message.setToUserId(0L);
        message.setCreateTime(new Date());
        message.setCreateBy(userId);
        // 保存数据
        this.save(message);
        // 调用OpenAI
        String questionAnswer = this.handleOpenAI(message);
        // 保存入库
        Message robotMessage = new Message();
        robotMessage.setFromUserId(0L);
        robotMessage.setToUserId(userId);
        robotMessage.setCreateTime(new Date());
        robotMessage.setCreateBy(0L);
        robotMessage.setMsgContent(questionAnswer);
        this.save(robotMessage);
        MessageResponse messageResponse = new MessageResponse();
        BeanUtil.copyProperties(robotMessage, messageResponse);
        return messageResponse;
    }

    @Override
    public IPage<MessageResponse> page(MessageRequest messageRequest, Integer current, Integer size) {
        Long userId = SubjectUtil.getUserId();
        IPage<Message> page = new Page<>(current, size);
        LambdaQueryWrapper<Message> query = new LambdaQueryWrapper<>();
        query.eq(Message::getFromUserId, userId).eq(Message::getToUserId, 0L);
        query.or().eq(Message::getFromUserId, 0).eq(Message::getToUserId, userId);
        query.orderByDesc(Message::getCreateTime);
        IPage<Message> message = this.page(page, query);
        // IPage<entity>->IPage<vo>
        IPage<MessageResponse> convert = message.convert(FriendMsg -> BeanUtil.copyProperties(FriendMsg, MessageResponse.class));
        return convert;
    }

    private String handleOpenAI(Message message){
        // 调用openai-gpt3-java方法
        OpenAiService service = new OpenAiService(OPENAPI_TOKEN, TIMEOUT);
        CompletionRequest.CompletionRequestBuilder builder = CompletionRequest.builder()
                .model(MODEL)
                .prompt(String.format(PROMPT, message.getMsgContent()))
                .temperature(TEMPERATURE)
                .maxTokens(1000)
                .topP(TOPP)
                .frequencyPenalty(FREQUENCYPENALTY)
                .presencePenalty(PRESENCEPENALTY);
        CompletionRequest completionRequest = builder.build();
        List<CompletionChoice> questionAnswer = service.createCompletion(completionRequest).getChoices();
        String endQuestionAnswer = "";
        for (CompletionChoice completionChoice : questionAnswer) {
            endQuestionAnswer = endQuestionAnswer.concat(completionChoice.getText());
        }
        return endQuestionAnswer;
    }
}
