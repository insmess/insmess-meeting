package com.insmess.meeting.controller;

import cn.hutool.core.util.StrUtil;
import com.insmess.meeting.common.api.CommonResult;
import com.insmess.meeting.common.component.storage.StorageService;
import com.insmess.meeting.common.constant.RoomFileConstant;
import com.insmess.meeting.common.entity.StorageEntity;
import com.insmess.meeting.entity.RoomFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

@Slf4j
@RestController
@Api(tags = "StorageController", description = "存储")
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;
    /**
     * 访问文件
     *
     * @param key 访问文件
     * @return
     */
    @ApiOperation("访问文件")
    @GetMapping("/fetch/{key}")
    public Object fetch(@PathVariable String key) {
        if (key == null) {
            return CommonResult.validateFailed("缺少核心参数key");
        }
        if (key.contains("../")) {
            return CommonResult.validateFailed("非法访问");
        }

        Resource file = storageService.loadAsResource(key);
        if (file == null) {
            return CommonResult.validateFailed("文件不存在");
        }
        String mediaType = storageService.getMediaType(key);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mediaType)).body(file);
    }


    @PostMapping("/upload")
    @ApiOperation("上传")
    public CommonResult upload(MultipartFile file) {
        //上传
        StorageEntity storage = storageService.store(file);
        //获取扩展名
        return CommonResult.success(storage);
    }


    /**
     * 测试实时保存语音内容使用
     * @param file
     * @return
     */
    @SneakyThrows
    @PostMapping("/uploadTmp")
    @ApiOperation("上传")
    public CommonResult uploadTmp(MultipartFile file) {
        //上传
        InputStream in = file.getInputStream();
        OutputStream out = new FileOutputStream("d:/aa.ogg");
        IOUtils.copy(in, out);
        out.close();
        in.close();
        //获取扩展名
        return CommonResult.success();
    }
}
