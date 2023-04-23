package com.ayuan.configcenterclient.spring;

import com.ayuan.configcenterclient.client.ConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ayuan
 * @Description: 自动装配，用来适配SpringCloud配置中心
 * @date 2023/4/23 13:45
 */
@Configuration
public class ConfigCenterBootstrapConfiguration {

    @Bean
    public ConfigCenterProperties configCenterProperties() {
        return new ConfigCenterProperties();
    }

    @Bean
    public ConfigService configService(ConfigCenterProperties configCenterProperties) {
        return new ConfigService(configCenterProperties.getServerAddress());
    }

    @Bean
    public ConfigContextRefresher configContextRefresher() {
        return new ConfigContextRefresher();
    }

}
