package com.ayuan.configcenterserver.strage;

import com.ayuan.configcenterserver.pojo.ConfigFile;

import java.util.List;

/**
 * @author Ayuan
 * @Description: TODO
 * @date 2023/4/23 10:51
 */
public class FileSystemConfigFileStorage implements ConfigFileStorage {




    @Override
    public void add(ConfigFile configFile) {

    }

    @Override
    public void update(ConfigFile configFile) {
    }

    @Override
    public void delete(String fileId) {
    }

    @Override
    public ConfigFile getByFileId(String fileId) {
        return null;
    }

    @Override
    public List<ConfigFile> getAll() {
        return null;
    }
}
