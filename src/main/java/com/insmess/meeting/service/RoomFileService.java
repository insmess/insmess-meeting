package com.insmess.meeting.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insmess.meeting.common.base.BaseService;
import com.insmess.meeting.dto.RoomFileNode;
import com.insmess.meeting.entity.RoomFile;
import java.util.List;

/**
 * 会议资料
 *
 * @author xujq
 * @email ${email}
 * @date 2024-05-12 17:16:45
 */
public interface RoomFileService extends BaseService<RoomFile> {

    /**
     * 获取树状列表
     * @param parentId 父级id
     * @return
     */
    List<RoomFileNode> treeList(String parentId, String roomId);


    /**
     * 根据parentId获取RoomFileNode列表
     * @param parentId
     * @return
     */
    List<RoomFileNode> listNodeByParentId(String parentId, String roomId);

    /**
     * 条件查询
     * @param roomFile 封装查询条件
     * @return 查询到的数据
     */
    List<RoomFile> list(RoomFile roomFile);

    /**
     * 条件分页查询
     * @param roomFile 封装查询条件
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return 查询到的数据
     */
    Page<RoomFile> page(RoomFile roomFile, Long pageNum, Long pageSize);
}

