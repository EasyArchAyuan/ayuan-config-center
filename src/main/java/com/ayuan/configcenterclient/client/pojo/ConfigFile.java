package com.ayuan.configcenterclient.client.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Ayuan
 * @Description: 配置文件的数据存储模型
 * @date 2023/4/23 10:35
 */
@Data
public class ConfigFile implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 文件的唯一id，由配置中心服务端在新增配置文件存储的时候自动生成，全局唯一
     */
    private String fileId;
    /**
     * 文件名
     */
    private String name;
    /**
     * 文件后缀名(.yml/.properties)
     */
    private String extension;
    /**
     * 配置文件内容
     */
    private String content;
    /**
     * 上一次文件更新的时间戳
     */
    private Long lastUpdateTimestamp;

}
