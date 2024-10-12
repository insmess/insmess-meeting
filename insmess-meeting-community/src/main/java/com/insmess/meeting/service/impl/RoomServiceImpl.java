package com.insmess.meeting.service.impl;

import cn.hutool.core.util.StrUtil;
import com.insmess.meeting.common.base.BaseServiceImpl;
import com.insmess.meeting.common.constant.RoomConstant;
import com.insmess.meeting.entity.Room;
import com.insmess.meeting.entity.RoomMemberRelation;
import com.insmess.meeting.mapper.RoomMapper;
import com.insmess.meeting.service.RoomMemberRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import com.insmess.meeting.service.RoomService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 会议室
 * @author xujq
 * @email ${email}
 * @date 2024-04-27 18:54:16
 */
@Slf4j
@Service
@Transactional
public class RoomServiceImpl extends BaseServiceImpl<RoomMapper, Room> implements RoomService {
    @Autowired
    private RoomMemberRelationService roomMemberRelationService;

    /**
     * 条件查询
     * @param room 封装查询条件
     * @return 查询到的数据
     */
    @Override
    public List<Room> list(Room room) {
        LambdaQueryWrapper<Room> qw = new QueryWrapper<Room>().lambda()
                .eq(room.getId() != null, Room::getId, room.getId())
                .eq(room.getName() != null, Room::getName, room.getName())
                .eq(room.getHostUsername() != null, Room::getHostUsername, room.getHostUsername())
                .eq(room.getHostRealname() != null, Room::getHostRealname, room.getHostRealname())
                .eq(room.getCreateTime() != null, Room::getCreateTime, room.getCreateTime())
                .eq(room.getStartTime() != null, Room::getStartTime, room.getStartTime())
                .eq(room.getCreateUsername() != null, Room::getCreateUsername, room.getCreateUsername())
                .eq(room.getCreateRealname() != null, Room::getCreateRealname, room.getCreateRealname())
                .eq(room.getRoomPassword() != null, Room::getRoomPassword, room.getRoomPassword())
                .eq(room.getRoomType() != null, Room::getRoomType, room.getRoomType());
                
        return list(qw);
    }

    /**
     * 条件分页查询
     * @param room 封装查询条件
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return 查询到的数据
     */
    @Override
    public Page<Room> page(Room room, Long pageNum, Long pageSize) {
        LambdaQueryWrapper<Room> qw = new QueryWrapper<Room>().lambda()
                .eq(room.getId() != null, Room::getId, room.getId())
                .eq(room.getName() != null, Room::getName, room.getName())
                .eq(room.getHostUsername() != null, Room::getHostUsername, room.getHostUsername())
                .eq(room.getHostRealname() != null, Room::getHostRealname, room.getHostRealname())
                .eq(room.getCreateTime() != null, Room::getCreateTime, room.getCreateTime())
                .eq(room.getStartTime() != null, Room::getStartTime, room.getStartTime())
                .eq(room.getCreateUsername() != null, Room::getCreateUsername, room.getCreateUsername())
                .eq(room.getCreateRealname() != null, Room::getCreateRealname, room.getCreateRealname())
                .eq(room.getRoomPassword() != null, Room::getRoomPassword, room.getRoomPassword())
                .eq(room.getRoomType() != null, Room::getRoomType, room.getRoomType());
                
        Page<Room> pageInfo = new Page<>(pageNum, pageSize);
        return page(pageInfo, qw);
    }

    @Override
    public Page<Room> pagePublicRoom(Long pageNum, Long pageSize) {
        Room room = new Room();
        room.setRoomType(RoomConstant.ROOM_TYPE_PUBLIC);
        return page(room, pageNum, pageSize);
    }

    @Override
    public void checkRoomPermission(String roomId, String username, String password) {
        Room room = getById(roomId);
        if (room == null) {
            throw new RuntimeException("会议室不存在：" + roomId);
        }
        //校验密码
        if (StrUtil.isNotBlank(room.getRoomPassword())) {
            checkRoomPassword(roomId, password);
        }
    }

    @Override
    public void checkRoomPassword(String roomId, String password) {
        Room room = getById(roomId);
        if (room.getRoomPassword() == null) {
            return;
        }
        if (!room.getRoomPassword().equals(password)) {
            throw new RuntimeException("会议密码错误");
        }
    }

    @Override
    public List<String> listMember(String roomId) {
        List<RoomMemberRelation> roomMemberRelationList = roomMemberRelationService.list(new QueryWrapper<RoomMemberRelation>().lambda().eq(RoomMemberRelation::getRoomId, roomId));
        if (roomMemberRelationList == null) {
            return null;
        }
        return roomMemberRelationList.stream().map(RoomMemberRelation::getUsername).collect(Collectors.toList());
    }

    @Override
    public String getRole(String roomId, String username) {
        Room room = getById(roomId);
        //判断是否是主持人
        if (room.getHostUsername().equals(username)) {
            return RoomConstant.ROOM_ROLE_HOST;
        }
        //判断是否是普通成员
//        List<String> memberUsernameList = listMember(roomId);
//        for (String memberUsername : memberUsernameList) {
//            if (memberUsername.equals(username)) {
//                return RoomConstant.ROOM_ROLE_MEMBER;
//            }
//        }
        return RoomConstant.ROOM_ROLE_MEMBER;
    }

    @Override
    public boolean save(Room entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(Room entity) {
        return super.updateById(entity);
    }
}