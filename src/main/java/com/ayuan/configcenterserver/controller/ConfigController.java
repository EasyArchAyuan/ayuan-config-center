package com.ayuan.configcenterserver.controller;

import com.ayuan.configcenterserver.pojo.ConfigFile;
import com.ayuan.configcenterserver.pojo.CreateConfigRequest;
import com.ayuan.configcenterserver.pojo.UpdateConfigRequest;
import com.ayuan.configcenterserver.service.ConfigServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ayuan
 * @Description: 配置接口
 * @date 2023/4/23 11:10
 */
@RestController
@RequestMapping("/v1/config")
public class ConfigController {

    @Autowired
    private ConfigServiceImpl configService;

    @PostMapping
    public String create(@RequestBody CreateConfigRequest createConfigRequest) {
        return configService.add(createConfigRequest.getName(), createConfigRequest.getExtension(), createConfigRequest.getContent());
    }

    @PutMapping
    public void update(@RequestBody UpdateConfigRequest updateConfigRequest) {
        configService.update(updateConfigRequest.getFileId(), updateConfigRequest.getName(), updateConfigRequest.getExtension(), updateConfigRequest.getContent());
    }

    @DeleteMapping("/{fileId}")
    public void delete(@PathVariable("fileId") String fileId) {
        configService.delete(fileId);
    }

    @GetMapping("/{fileId}")
    public ConfigFile select(@PathVariable("fileId") String fileId) {
        return configService.selectByFileId(fileId);
    }

    @GetMapping("/all")
    public List<ConfigFile> selectAll() {
        return configService.selectAll();
    }

}
