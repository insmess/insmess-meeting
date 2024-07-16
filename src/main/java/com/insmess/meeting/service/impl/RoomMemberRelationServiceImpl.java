package com.insmess.meeting.service.impl;

import com.insmess.meeting.common.base.BaseServiceImpl;
import com.insmess.meeting.entity.RoomMemberRelation;
import com.insmess.meeting.mapper.RoomMemberRelationMapper;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import com.insmess.meeting.service.RoomMemberRelationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * 会议室成员关联
 * @author xujq
 * @email ${email}
 * @date 2024-04-27 18:54:16
 */
@Slf4j
@Service
@Transactional
public class RoomMemberRelationServiceImpl extends BaseServiceImpl<RoomMemberRelationMapper, RoomMemberRelation> implements RoomMemberRelationService {

    /**
     * 条件查询
     * @param roomMemberRelation 封装查询条件
     * @return 查询到的数据
     */
    @Override
    public List<RoomMemberRelation> list(RoomMemberRelation roomMemberRelation) {
        LambdaQueryWrapper<RoomMemberRelation> qw = new QueryWrapper<RoomMemberRelation>().lambda()
                .eq(roomMemberRelation.getId() != null, RoomMemberRelation::getId, roomMemberRelation.getId())
                .eq(roomMemberRelation.getRoomId() != null, RoomMemberRelation::getRoomId, roomMemberRelation.getRoomId())
                .eq(roomMemberRelation.getUsername() != null, RoomMemberRelation::getUsername, roomMemberRelation.getUsername());
                
        return list(qw);
    }

    /**
     * 条件分页查询
     * @param roomMemberRelation 封装查询条件
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return 查询到的数据
     */
    @Override
    public Page<RoomMemberRelation> page(RoomMemberRelation roomMemberRelation, Long pageNum, Long pageSize) {
        LambdaQueryWrapper<RoomMemberRelation> qw = new QueryWrapper<RoomMemberRelation>().lambda()
                .eq(roomMemberRelation.getId() != null, RoomMemberRelation::getId, roomMemberRelation.getId())
                .eq(roomMemberRelation.getRoomId() != null, RoomMemberRelation::getRoomId, roomMemberRelation.getRoomId())
                .eq(roomMemberRelation.getUsername() != null, RoomMemberRelation::getUsername, roomMemberRelation.getUsername());
                
        Page<RoomMemberRelation> pageInfo = new Page<>(pageNum, pageSize);
        return page(pageInfo, qw);
    }
}