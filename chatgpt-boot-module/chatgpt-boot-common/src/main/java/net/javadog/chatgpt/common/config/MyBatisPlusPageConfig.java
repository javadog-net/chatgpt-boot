package net.javadog.chatgpt.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: hdx
 * @Date: 2023-01-17 10:23
 * @version: 1.0
 **/
@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MyBatisPlusPageConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
