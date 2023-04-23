package com.ayuan.configcenterclient.spring;

import com.ayuan.configcenterclient.client.ConfigService;
import com.ayuan.configcenterclient.client.pojo.ConfigFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Ayuan
 * @Description: 注册到spring-cloud核心接口
 * @date 2023/4/23 13:22
 */
@Component
public class ConfigCenterPropertySourceLocator implements PropertySourceLocator {

    private final List<PropertySourceLoader> propertySourceLoaderList = SpringFactoriesLoader.loadFactories(PropertySourceLoader.class, Thread.currentThread().getContextClassLoader());

    @Autowired
    private ConfigService configService;
    @Autowired
    private ConfigCenterProperties configCenterProperties;


    @Override
    public PropertySource<?> locate(Environment environment) {
        CompositePropertySource composite = new CompositePropertySource("ayuan");
        try {
            loadConfig(composite, configCenterProperties.getFileId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return composite;
    }

    private void loadConfig(CompositePropertySource composite, String fileId) throws IOException {
        //1.从配置中心拉配置
        ConfigFile config = configService.getConfig(fileId);
        if (Objects.isNull(config)) {
            throw new RuntimeException(fileId + "未找到对应配置文件");
        }
        //2.解析配置文件
        for (PropertySourceLoader propertySourceLoader : propertySourceLoaderList) {
            //包含所需配置扩展名
            if (Arrays.asList(propertySourceLoader.getFileExtensions()).contains(config.getExtension())) {
                List<PropertySource<?>> load = propertySourceLoader.load(config.getFileId(), new ByteArrayResource(config.getContent().getBytes(StandardCharsets.ISO_8859_1)));
                for (PropertySource<?> propertySource : load) {
                    composite.addFirstPropertySource(propertySource);
                }
            }
        }
    }
}
