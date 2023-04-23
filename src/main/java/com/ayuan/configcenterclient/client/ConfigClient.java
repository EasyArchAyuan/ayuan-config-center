package com.ayuan.configcenterclient.client;

import com.ayuan.configcenterclient.client.pojo.ConfigFile;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ayuan
 * @Description: pull拉取（长轮询）
 * @date 2023/4/23 11:43
 */
public class ConfigClient {
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * ip+端口号
     */
    private final String serverAddress;

    public ConfigClient(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public ConfigFile getConfig(String fileId) {
        return restTemplate.getForObject("http://" + serverAddress + "/v1/config/" + fileId, ConfigFile.class);
    }

}
