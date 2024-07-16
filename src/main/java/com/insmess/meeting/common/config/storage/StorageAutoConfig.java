package com.insmess.meeting.common.config.storage;

import com.insmess.meeting.common.component.storage.LocalStorage;
import com.insmess.meeting.common.component.storage.StorageService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageAutoConfig {

    //初始化构造方法装配
    private final StorageProperties properties;

    public StorageAutoConfig(StorageProperties properties) {
        this.properties = properties;
    }

    @Bean
    public StorageService storageService() {
        StorageService storageService = new StorageService();
        return storageService;
    }

    @Bean
    public LocalStorage localStorage() {
        LocalStorage localStorage = new LocalStorage();
        localStorage.setAddress(this.properties.getAddress());
        localStorage.setStoragePath(this.properties.getStoragePath());
        return localStorage;
    }
}
