package com.ayuan.configcenterclient.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @author Ayuan
 * @Description: 测试
 * @date 2023/4/23 14:29
 */

@RefreshScope
@Service
public class UserService {

    @Value("${ayuan.username}")
    private String username;

    public String getUsername() {
        return username;
    }
}
