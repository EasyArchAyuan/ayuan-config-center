package com.ayuan.configcenterserver.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Ayuan
 * @Description: 新增配置请求
 * @date 2023/4/23 11:24
 */
@Getter
@Setter
@Accessors(chain = true)
//对应字段的 setter 方法调用后，会返回当前对象。
public class CreateConfigRequest {

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件后缀名
     */
    private String extension;

    /**
     * 文件的内容
     */
    private String content;
}
