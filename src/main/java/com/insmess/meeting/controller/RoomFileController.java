package com.insmess.meeting.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insmess.meeting.common.api.CommonResult;
import com.insmess.meeting.common.component.storage.StorageService;
import com.insmess.meeting.common.constant.RoomFileConstant;
import com.insmess.meeting.common.entity.StorageEntity;
import com.insmess.meeting.dto.RoomFileNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Arrays;
import com.insmess.meeting.entity.RoomFile;
import com.insmess.meeting.service.RoomFileService;
import org.springframework.web.multipart.MultipartFile;


/**
 * 会议资料
 *
 * @author xujq
 * @email ${email}
 * @date 2024-05-12 17:16:45
 */
@Slf4j
@RestController
@Api(tags = "RoomFileController", description = "会议资料")
@RequestMapping("/roomFile")
public class RoomFileController {

    @Autowired
    private RoomFileService roomFileService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/treeList")
    @ApiOperation("获取列表（树结构）")
    public CommonResult treeList(String parentId, String roomId) {
        List<RoomFileNode> roomFileNodeList = roomFileService.treeList(parentId, roomId);
        return CommonResult.success(roomFileNodeList);
    }

    @PostMapping("/mkdir")
    @ApiOperation("创建目录")
    public CommonResult mkdir(String dirName, String parentId, String roomId) {
        RoomFile roomFile = new RoomFile();
        roomFile.setFilename(dirName);
        roomFile.setCreateTime(new Date());
        roomFile.setRoomId(roomId);
        roomFile.setParentId(parentId);
        roomFile.setEntityType(RoomFileConstant.ROOM_FILE_ENTITY_TYPE_DIRECTORY);
        roomFileService.save(roomFile);
        RoomFileNode roomFileNode = new RoomFileNode();
        BeanUtils.copyProperties(roomFile, roomFileNode);
        return CommonResult.success(roomFileNode);
    }

    @PostMapping("/upload")
    @ApiOperation("上传")
    public CommonResult upload(MultipartFile file, String parentId, String roomId) {
        if (StrUtil.isBlank(parentId) || StrUtil.isBlank(roomId)) {
            return CommonResult.failed("缺少必须参数");
        }
        //上传
        StorageEntity storage = storageService.store(file);
        RoomFile roomFile = new RoomFile();
        roomFile.setFileSize(file.getSize());
        roomFile.setFileUrl(storage.getUrl());
        roomFile.setFilename(storage.getFileName());
        roomFile.setCreateTime(new Date());
        //获取扩展名
        String fileType = "未知";
        int i = roomFile.getFilename().lastIndexOf(".");
        if (i != -1) {
            fileType = roomFile.getFilename().substring(i + 1);
        }
        roomFile.setFileType(fileType);
        roomFile.setParentId(parentId);
        roomFile.setRoomId(roomId);
        roomFile.setEntityType(RoomFileConstant.ROOM_FILE_ENTITY_TYPE_FILE);
        roomFileService.save(roomFile);
        return CommonResult.success(storage);
    }

    /**
     * 通过id获取
     */
    @GetMapping("/findById")
    @ApiOperation("通过id获取")
    public CommonResult<RoomFile> findById(String id){
        RoomFile roomFile = roomFileService.getById(id);
        if(null == roomFile){
            return CommonResult.failed("没有查询到值");
        }
        return CommonResult.success(roomFile);
    }

    /**
     * 分页查询获取
     */
    @GetMapping("/findPage")
    @ApiOperation("分页查询获取")
    public CommonResult<Page> findPage(RoomFile roomFile,
                                        @RequestParam(defaultValue = "1") Long pageNum,
                                        @RequestParam(defaultValue = "10") Long pageSize){
        Page page = roomFileService.page(roomFile, pageNum, pageSize);
        if(null == page){
            return CommonResult.failed("没有查询到值");
        }
        return CommonResult.success(page);
    }

    /**
     * 获取数据列表
     */
    @GetMapping("/findList")
    @ApiOperation("获取数据列表")
    public CommonResult<List<RoomFile>> findList(RoomFile roomFile){

        List<RoomFile> list = roomFileService.list(roomFile);
        if(null == list){
            return CommonResult.failed("没有查询到值");
        }
        return CommonResult.success(list);
    }

    /**
     * 添加数据
     */
    @PostMapping("/save")
    @ApiOperation("保存数据")
    public CommonResult<RoomFile> save(@RequestBody RoomFile roomFile){
        if(roomFileService.save(roomFile)){
            return CommonResult.success(roomFile);
        }
        return CommonResult.failed("保存或更新失败");
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    @ApiOperation("修改数据")
    public CommonResult<RoomFile> update(@RequestBody RoomFile roomFile){
        if(roomFileService.updateById(roomFile)){
            return CommonResult.success(roomFile);
        }
        return CommonResult.failed("修改失败");
    }

    /**
     *保存或更新数据。参数存在id，则修改。参数不存在id，则添加。
     */
    @PostMapping("/saveOrUpdate")
    @ApiOperation("保存或更新数据")
    public CommonResult<RoomFile> saveOrUpdate(@RequestBody RoomFile roomFile){

        if(roomFileService.saveOrUpdate(roomFile)){
            return CommonResult.success(roomFile);
        }
        return CommonResult.failed("保存或更新失败");
    }

    /**
     *通过id删除
     */
    @PostMapping("/deleteById")
    @ApiOperation("通过id删除")
    public CommonResult deleteById(String id){

        if(roomFileService.removeById(id)){
            return CommonResult.success("删除成功");
        }else{
            return CommonResult.failed("删除失败");
        }
    }

}
