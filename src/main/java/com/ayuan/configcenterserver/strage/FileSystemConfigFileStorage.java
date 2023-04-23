package com.ayuan.configcenterserver.strage;

import com.alibaba.fastjson.JSON;
import com.ayuan.configcenterserver.pojo.ConfigFile;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ayuan
 * @Description: TODO
 * @date 2023/4/23 10:51
 */
@Slf4j
public class FileSystemConfigFileStorage implements ConfigFileStorage {
    /**
     * 文件的存储路径
     */
    private final String fileStorePath = System.getProperty("user.home") + File.separator + "ayuan"
            + File.separator + "config";

    /**
     * 缓存，减少与磁盘交互，查询操作直接从缓存中查找即可
     */
    private final ConfigFileStorage cache = new InMemoryConfigFileStorage();

    /**
     * 一个配置文件对应一个磁盘文件
     */
    private final Map<String, File> fileMap = new ConcurrentHashMap<>();


    /**
     * 刚启动时，从磁盘加载文件到内存中
     */
    @PostConstruct
    public void loadFiles() {
        log.info("配置文件存储路径为:{}", fileStorePath);
        File dir = new File(fileStorePath);
        dir.mkdirs();
        File[] files = dir.listFiles();
        if (files != null) {
            Arrays.stream(files).forEach(this::loadFile);
        }
    }

    @Override
    public void add(ConfigFile configFile) {
        //1.获取文件ID
        String fileId = configFile.getFileId();
        //2.新建文件并写入
        File file = new File(fileStorePath + File.separator + fileId);
        writeToFile(file, configFile);
        //3.保存到fileMap中
        fileMap.put(fileId, file);
        //4.更新缓存
        cache.add(configFile);
    }

    @Override
    public void update(ConfigFile configFile) {
        String fileId = configFile.getFileId();
        File file = getFile(fileId);
        writeToFile(file, configFile);
        cache.update(configFile);
    }

    @Override
    public void delete(String fileId) {
        File file = getFile(fileId);
        file.delete();
        cache.delete(fileId);
    }

    @Override
    public ConfigFile getByFileId(String fileId) {
        return cache.getByFileId(fileId);
    }

    @Override
    public List<ConfigFile> getAll() {
        return cache.getAll();
    }

    /**
     * 读文件
     *
     * @param file
     */
    private void loadFile(File file) {
        byte[] bytes = new byte[(int) file.length()];
        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(file.toPath()));) {
            bis.read(bytes);
        } catch (IOException e) {
            log.error("loadFile 异常:{}", e.getMessage(), e);
        }
        ConfigFile configFile = JSON.parseObject(new String(bytes), ConfigFile.class);
        fileMap.put(configFile.getFileId(), file);
        cache.add(configFile);
    }

    /**
     * 写文件
     *
     * @param file
     * @param configFile
     */
    private void writeToFile(File file, ConfigFile configFile) {
        String json = JSON.toJSONString(configFile);
        try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(file.toPath()));) {
            bos.write(json.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("writeToFile 异常:{}", e.getMessage(), e);
        }
    }

    /**
     * 根据ID获取文件
     *
     * @param fileId
     * @return
     */
    private File getFile(String fileId) {
        File file = fileMap.get(fileId);
        if (Objects.isNull(file)) {
            throw new RuntimeException("文件id=" + fileId + "未找到对应的文件");
        }
        return file;
    }
}
