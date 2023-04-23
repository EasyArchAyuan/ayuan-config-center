package com.ayuan.configcenterclient.spring;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.Serializable;

/**
 * @author Ayuan
 * @Description: 配置中心的配置信息
 * @date 2023/4/23 13:19
 */
@Getter
@Setter
@ConfigurationProperties("spring.cloud.ayuan.config")
public class ConfigCenterProperties implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 配置中心地址
     */
    private String serverAddress;

    /**
     * 配置文件ID
     */
    private String fileId;

}
