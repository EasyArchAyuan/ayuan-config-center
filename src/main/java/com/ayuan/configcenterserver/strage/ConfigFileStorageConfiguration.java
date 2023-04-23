package com.ayuan.configcenterserver.strage;

import com.ayuan.configcenterserver.config.ConfigServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ayuan
 * @Description: 文件存储配置实现
 * @date 2023/4/23 10:43
 */
@Configuration
public class ConfigFileStorageConfiguration {

    @Autowired
    private ConfigServerProperties configServerProperties;

    @Bean
    @ConditionalOnMissingBean
    //ConditionalOnMissingBean保证单例
    public ConfigFileStorage configFileStorage() {
        String storeType = configServerProperties.getStoreType().toLowerCase();
        if ("file".equals(storeType)) {
            return new FileSystemConfigFileStorage();
        } else if ("memory".equals(storeType)) {
            return new InMemoryConfigFileStorage();
        } else {
            throw new RuntimeException("storeType=" + storeType + "无对应的ConfigFileStorage实现");
        }
    }
}
