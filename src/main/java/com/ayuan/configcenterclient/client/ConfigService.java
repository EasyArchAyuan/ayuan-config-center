package com.ayuan.configcenterclient.client;

import com.ayuan.configcenterclient.client.listener.ConfigFileChangedListener;
import com.ayuan.configcenterclient.client.pojo.ConfigFile;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Ayuan
 * @Description: TODO
 * @date 2023/4/23 11:49
 */
public class ConfigService {
    private static final ScheduledExecutorService EXECUTOR;

    static {
        EXECUTOR = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            private final AtomicLong atomicLong = new AtomicLong();

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "ConfigService-" + atomicLong.getAndIncrement());
                thread.setDaemon(true);
                return thread;
            }
        });
    }

    /**
     * 文件id和对应监听器映射
     */
    private final Map<String, ConfigFileChangedListener> configFileChangeListenerMap = new ConcurrentHashMap<>();

    /**
     * 文件id和文件的映射
     */
    private final Map<String, ConfigFile> configFileCache = new ConcurrentHashMap<>();

    /**
     * 跟服务端交互的client
     */
    private final ConfigClient configClient;

    public ConfigService(String serverAddress) {
        this.configClient = new ConfigClient(serverAddress);
        initTask();
    }

    /**
     * 添加一个监听器
     *
     * @param fileId
     * @param configFileChangeListener
     */
    public void addListener(String fileId, ConfigFileChangedListener configFileChangeListener) {
        configFileChangeListenerMap.put(fileId, configFileChangeListener);
    }

    public ConfigFile getConfig(String fileId) {
        return configClient.getConfig(fileId);
    }

    private void initTask() {
        //每隔5s中从配置中心拉取文件，判断文件是不是有更新，如果有更新就回调对应的监听器
        EXECUTOR.scheduleWithFixedDelay(this::notifyChangedConfigFile, 1, 5, TimeUnit.SECONDS);
    }

    private void notifyChangedConfigFile() {
        for (Map.Entry<String, ConfigFileChangedListener> entry : configFileChangeListenerMap.entrySet()) {
            String fileId = entry.getKey();
            ConfigFile configFile = configClient.getConfig(fileId);
            ConfigFile cachedConfigFile = configFileCache.get(fileId);
            if (cachedConfigFile != null && configFile != null) {
                if (cachedConfigFile.getLastUpdateTimestamp() < configFile.getLastUpdateTimestamp()) {
                    //当发现缓存的数据更新的时间小于最新查询的文件的更新时间，说明配置文件有更新，然后回调对应的监听器
                    entry.getValue().onFileChanged(configFile);
                }
            }
            configFileCache.put(fileId, configFile);
        }
    }

}
