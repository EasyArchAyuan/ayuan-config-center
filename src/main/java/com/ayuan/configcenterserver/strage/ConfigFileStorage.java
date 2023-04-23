package com.ayuan.configcenterserver.strage;

import com.ayuan.configcenterserver.pojo.ConfigFile;

import java.util.List;

/**
 * @author Ayuan
 * @Description: 配置存储的crud操作
 * @date 2023/4/23 10:40
 */
public interface ConfigFileStorage {

    /**
     * 保存配置
     * @param configFile
     */
    void add(ConfigFile configFile);

    /**
     * 更新配置
     * @param configFile
     */
    void update(ConfigFile configFile);

    /**
     * 删除配置
     * @param fileId
     */
    void delete(String fileId);

    /**
     * 根据文件ID获取配置
     * @param fileId
     * @return
     */
    ConfigFile getByFileId(String fileId);

    /**
     * 获取全部配置
     * @return
     */
    List<ConfigFile> getAll();

}
