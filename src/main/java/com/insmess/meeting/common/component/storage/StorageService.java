package com.insmess.meeting.common.component.storage;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.insmess.meeting.common.entity.StorageEntity;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * 提供存储服务类，所有存储服务均由该类对外提供
 */
public class StorageService {

    @Autowired
    private Storage storage;

    /**
     * 上传操作，返回值为文件系统的各种信息。
     * @param multipartFile
     * @return StorageEntity信息。
     */
    public StorageEntity store(MultipartFile multipartFile) {
        return store(multipartFile, null);
    }

    /**
     * 上传操作，返回值为文件系统的各种信息。
     * @param multipartFile
     * @return StorageEntity信息。
     */
    public StorageEntity store(MultipartFile multipartFile, String key) {
        //获取文件名
        String originalFilename = multipartFile.getOriginalFilename();
        //获取文件流
        InputStream in = null;
        try {
            in = multipartFile.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("获取文件流失败");
        }
        //获取文件类型
        String contentType = multipartFile.getContentType();
        long size = multipartFile.getSize();
        return store(in, contentType, size ,originalFilename, key);
    }

    /**
     * 根据keyName获取媒体类型
     * @param keyName
     * @return
     */
    public String getMediaType(String keyName) {
        Tika tika = new Tika();
        Path path = load(keyName);
        String mediaType = null;
        try {
            mediaType = tika.detect(path);
        } catch (IOException e) {
            throw new RuntimeException("该文件不存在");
        }
        return mediaType;
    }


    /**
     * 存储一个文件对象
     *
     * @param inputStream   文件输入流
     * @param fileName      文件名
     */
    public StorageEntity store(InputStream inputStream, String type, long size, String fileName) {
        return store(inputStream, type, size, fileName, null);
    }

    /**
     * 存储一个文件对象
     *
     * @param inputStream   文件输入流
     * @param fileName      文件名
     * @param key      存储在本地的文件名
     */
    public StorageEntity store(InputStream inputStream, String type, long size, String fileName, String key) {
        if (StringUtils.isBlank(key)) {
            key = generateKey(fileName);
        }
        storage.store(inputStream, key);
        String url = generateUrl(key);
        StorageEntity storageInfo = new StorageEntity();
        storageInfo.setFileName(fileName);
        storageInfo.setUrl(url);
        storageInfo.setKey(key);
        storageInfo.setType(type);
        storageInfo.setSize(size);
        storageInfo.setCreateTime(new Date());
        return storageInfo;
    }

    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);

        String key = null;
        StorageEntity storageInfo = null;

        key = UUID.randomUUID().toString().replace("-", "") + suffix;

//        do {
//            key = UUID.randomUUID().toString().replace("-", "") + suffix;
//            storageInfo = baseStorageService.findByKey(key);
//        }
//        while (storageInfo != null);

        return key;
    }

    public Stream<Path> loadAll() {
        return storage.loadAll();
    }

    /**
     *
     * @param keyName 文件系统中存储的文件名
     * @return
     */
    public Path load(String keyName) {
        return storage.load(keyName);
    }

    public Resource loadAsResource(String keyName) {
        return storage.loadAsResource(keyName);
    }

    public void delete(String keyName) {
        storage.delete(keyName);
    }

    private String generateUrl(String keyName) {
        return storage.generateUrl(keyName);
    }
}
