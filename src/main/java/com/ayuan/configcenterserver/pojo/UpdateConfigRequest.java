package com.ayuan.configcenterserver.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Ayuan
 * @Description: 修改配置请求
 * @date 2023/4/23 11:25
 */
@Getter
@Setter
@Accessors(chain = true)
public class UpdateConfigRequest extends CreateConfigRequest {

    /**
     * 文件名
     */
    private String fileId;

}