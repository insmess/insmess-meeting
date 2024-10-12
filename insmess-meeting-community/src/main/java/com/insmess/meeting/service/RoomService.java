package com.insmess.meeting.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insmess.meeting.common.base.BaseService;
import com.insmess.meeting.entity.Room;

import java.util.List;

/**
 * 会议室
 *
 * @author xujq
 * @email ${email}
 * @date 2024-04-27 18:54:16
 */
public interface RoomService extends BaseService<Room> {

    /**
     * 条件查询
     * @param room 封装查询条件
     * @return 查询到的数据
     */
    List<Room> list(Room room);

    /**
     * 条件分页查询
     * @param room 封装查询条件
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return 查询到的数据
     */
    Page<Room> page(Room room, Long pageNum, Long pageSize);

    /**
     * 条件分页查询公共会议室
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return 查询到的数据
     */
    Page<Room> pagePublicRoom(Long pageNum, Long pageSize);

    /**
     * 根据会议室id检查该用户是否具备权限
     * @param roomId
     * @param username
     * @param password
     */
    void checkRoomPermission(String roomId, String username, String password);

    /**
     * 检查会议密码
     * @param roomId 会议ID
     * @param password 会议密码
     */
    void checkRoomPassword(String roomId, String password);

    /**
     * 获取所有普通成员
     * @param roomId 会议ID
     */
    List<String> listMember(String roomId);

    /**
     * 获取会议角色
     * @param roomId 会议ID
     * @param username 用户名
     */
    String getRole(String roomId, String username);
}

