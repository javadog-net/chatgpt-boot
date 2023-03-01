package net.javadog.chatgpt.common.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * WebMvcConfig配置
 *
 * @author: hdx
 * @Date: 2023-01-07 16:07
 * @version: 1.0
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 上传的文件对外暴露的访问路径
     */
    @Value("${file.access-path-pattern}")
    private String accessPathPattern;

    /**
     * 文件上传目录
     */
    @Value("${file.upload-folder}")
    private String uploadFolder;

    /**
     * 配置本地文件上传的虚拟路径
     * 备注：这是一种图片上传访问图片的方法，实际上也可以使用nginx反向代理访问图片
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 文件上传
        uploadFolder = StrUtil.concat(true, uploadFolder, File.separator);
        registry.addResourceHandler(accessPathPattern)
                .addResourceLocations("file:" + uploadFolder);
    }
}
