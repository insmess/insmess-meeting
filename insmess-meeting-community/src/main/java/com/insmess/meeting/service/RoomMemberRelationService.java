package com.insmess.meeting.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insmess.meeting.common.base.BaseService;
import com.insmess.meeting.entity.RoomMemberRelation;

import java.util.List;

/**
 * 会议室成员关联
 *
 * @author xujq
 * @email ${email}
 * @date 2024-04-27 18:54:16
 */
public interface RoomMemberRelationService extends BaseService<RoomMemberRelation> {

    /**
     * 条件查询
     * @param roomMemberRelation 封装查询条件
     * @return 查询到的数据
     */
    List<RoomMemberRelation> list(RoomMemberRelation roomMemberRelation);

    /**
     * 条件分页查询
     * @param roomMemberRelation 封装查询条件
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return 查询到的数据
     */
    Page<RoomMemberRelation> page(RoomMemberRelation roomMemberRelation, Long pageNum, Long pageSize);
}

