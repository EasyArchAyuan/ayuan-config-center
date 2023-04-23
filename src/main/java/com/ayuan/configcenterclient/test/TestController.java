package com.ayuan.configcenterclient.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ayuan
 * @Description: 测试controller
 * @date 2023/4/23 14:31
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private UserService userService;

    @GetMapping
    public String get() {
        return userService.getUsername();
    }

}
