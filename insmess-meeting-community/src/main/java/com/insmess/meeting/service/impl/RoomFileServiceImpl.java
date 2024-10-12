package com.insmess.meeting.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.insmess.meeting.common.base.BaseServiceImpl;
import com.insmess.meeting.common.constant.RoomFileConstant;
import com.insmess.meeting.dto.RoomFileNode;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import com.insmess.meeting.entity.RoomFile;
import com.insmess.meeting.service.RoomFileService;
import com.insmess.meeting.mapper.RoomFileMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 会议资料
 * @author xujq
 * @email ${email}
 * @date 2024-05-12 17:16:45
 */
@Slf4j
@Service
@Transactional
public class RoomFileServiceImpl extends BaseServiceImpl<RoomFileMapper, RoomFile> implements RoomFileService {

    @Override
    public List<RoomFileNode> treeList(String parentId, String roomId) {
        //根据parentId获取
        List<RoomFileNode> roomFileNodeList = listNodeByParentId(parentId, roomId);
        //遍历，如果是目录，继续获取下级
        for (RoomFileNode roomFileNode : roomFileNodeList) {
            if (roomFileNode.getEntityType().equals(RoomFileConstant.ROOM_FILE_ENTITY_TYPE_DIRECTORY)) {
                List<RoomFileNode> childList = treeList(roomFileNode.getId(), roomId);
                roomFileNode.setChildren(childList);
            }
        }
        return roomFileNodeList;
    }

    @Override
    public List<RoomFileNode> listNodeByParentId(String parentId, String roomId) {
        //获取列表
        List<RoomFile> roomFileList = list(new QueryWrapper<RoomFile>().lambda().eq(RoomFile::getParentId, parentId).eq(RoomFile::getRoomId, roomId));
        roomFileList.sort(Comparator.comparingInt(RoomFile::getEntityType));
        List<RoomFileNode> roomFileNodeList = new ArrayList<>();
        //克隆
        for (RoomFile roomFile : roomFileList) {
            RoomFileNode roomFileNode = new RoomFileNode();
            BeanUtil.copyProperties(roomFile, roomFileNode);
            roomFileNodeList.add(roomFileNode);
        }
        return roomFileNodeList;
    }

    /**
     * 条件查询
     * @param roomFile 封装查询条件
     * @return 查询到的数据
     */
    @Override
    public List<RoomFile> list(RoomFile roomFile) {
        LambdaQueryWrapper<RoomFile> qw = new QueryWrapper<RoomFile>().lambda()
                .eq(roomFile.getId() != null, RoomFile::getId, roomFile.getId())
                .eq(roomFile.getFilename() != null, RoomFile::getFilename, roomFile.getFilename())
                .eq(roomFile.getCreateTime() != null, RoomFile::getCreateTime, roomFile.getCreateTime())
                .eq(roomFile.getCreateUserId() != null, RoomFile::getCreateUserId, roomFile.getCreateUserId())
                .eq(roomFile.getFileType() != null, RoomFile::getFileType, roomFile.getFileType())
                .eq(roomFile.getFileUrl() != null, RoomFile::getFileUrl, roomFile.getFileUrl())
                .eq(roomFile.getRoomId() != null, RoomFile::getRoomId, roomFile.getRoomId())
                .eq(roomFile.getParentId() != null, RoomFile::getParentId, roomFile.getParentId())
                .eq(roomFile.getEntityType() != null, RoomFile::getEntityType, roomFile.getEntityType())
                .eq(roomFile.getFileSize() != null, RoomFile::getFileSize, roomFile.getFileSize());
                
        return list(qw);
    }

    /**
     * 条件分页查询
     * @param roomFile 封装查询条件
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return 查询到的数据
     */
    @Override
    public Page<RoomFile> page(RoomFile roomFile, Long pageNum, Long pageSize) {
        LambdaQueryWrapper<RoomFile> qw = new QueryWrapper<RoomFile>().lambda()
                .eq(roomFile.getId() != null, RoomFile::getId, roomFile.getId())
                .eq(roomFile.getFilename() != null, RoomFile::getFilename, roomFile.getFilename())
                .eq(roomFile.getCreateTime() != null, RoomFile::getCreateTime, roomFile.getCreateTime())
                .eq(roomFile.getCreateUserId() != null, RoomFile::getCreateUserId, roomFile.getCreateUserId())
                .eq(roomFile.getFileType() != null, RoomFile::getFileType, roomFile.getFileType())
                .eq(roomFile.getFileUrl() != null, RoomFile::getFileUrl, roomFile.getFileUrl())
                .eq(roomFile.getRoomId() != null, RoomFile::getRoomId, roomFile.getRoomId())
                .eq(roomFile.getParentId() != null, RoomFile::getParentId, roomFile.getParentId())
                .eq(roomFile.getEntityType() != null, RoomFile::getEntityType, roomFile.getEntityType())
                .eq(roomFile.getFileSize() != null, RoomFile::getFileSize, roomFile.getFileSize());
                
        Page<RoomFile> pageInfo = new Page<>(pageNum, pageSize);
        return page(pageInfo, qw);
    }
}