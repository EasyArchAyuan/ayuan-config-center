package com.ayuan.configcenterserver.strage;

import com.ayuan.configcenterserver.pojo.ConfigFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ayuan
 * @Description: TODO
 * @date 2023/4/23 10:51
 */
public class InMemoryConfigFileStorage implements ConfigFileStorage {

    private final Map<String, ConfigFile> configFileMap = new ConcurrentHashMap<>();

    @Override
    public void add(ConfigFile configFile) {
        configFileMap.put(configFile.getFileId(), configFile);
    }

    @Override
    public void update(ConfigFile configFile) {
        configFileMap.put(configFile.getFileId(), configFile);
    }

    @Override
    public void delete(String fileId) {
        configFileMap.remove(fileId);
    }

    @Override
    public ConfigFile getByFileId(String fileId) {
        return configFileMap.get(fileId);
    }

    @Override
    public List<ConfigFile> getAll() {
        return new ArrayList<>(configFileMap.values());
    }
}
