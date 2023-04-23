package com.ayuan.configcenterclient.client.listener;

import com.ayuan.configcenterclient.client.pojo.ConfigFile;

/**
 * @author Ayuan
 * @Description: 文件内容变化监听器
 * @date 2023/4/23 11:41
 */
public interface ConfigFileChangedListener {

    /**
     *当文件内容有变动的话，就会回调
     *
     * @param configFile
     */
    void onFileChanged(ConfigFile configFile);
}
