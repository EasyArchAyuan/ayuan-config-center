package com.ayuan.configcenterserver.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Ayuan
 * @Description: 配置文件
 * @date 2023/4/23 10:45
 */
//@ConfigurationProperties("config")
@Component
@Data
public class ConfigServerProperties {
    /**
     * 存储类型 ，memory:基于内存存储 file:文件系统存储
     */
    private String storeType = "file";
}
