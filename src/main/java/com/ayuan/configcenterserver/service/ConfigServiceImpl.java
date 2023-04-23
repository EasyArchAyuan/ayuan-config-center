package com.ayuan.configcenterserver.service;

import com.ayuan.configcenterserver.pojo.ConfigFile;
import com.ayuan.configcenterserver.strage.ConfigFileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Ayuan
 * @Description: 配置中心实现类
 * @date 2023/4/23 11:05
 */
@Service
public class ConfigServiceImpl {

    @Autowired
    private ConfigFileStorage configFileStorage;


    public String add(String name, String extension, String content) {
        ConfigFile configFile = new ConfigFile();
        configFile.setFileId(UUID.randomUUID().toString());
        configFile.setName(name);
        configFile.setExtension(extension);
        configFile.setContent(content);
        configFile.setLastUpdateTimestamp(System.currentTimeMillis());
        configFileStorage.add(configFile);
        return configFile.getFileId();
    }

    public void update(String fileId, String name, String extension, String content) {
        ConfigFile configFile = new ConfigFile();
        configFile.setFileId(fileId);
        configFile.setName(name);
        configFile.setExtension(extension);
        configFile.setContent(content);
        configFile.setLastUpdateTimestamp(System.currentTimeMillis());
        configFileStorage.update(configFile);
    }

    public void delete(String fileId) {
        configFileStorage.delete(fileId);
    }

    public ConfigFile selectByFileId(String fileId) {
        return configFileStorage.getByFileId(fileId);
    }

    public List<ConfigFile> selectAll() {
        return configFileStorage.getAll();
    }
}
