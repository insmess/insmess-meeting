package com.insmess.meeting.mapper;

import com.insmess.meeting.entity.RoomMemberRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会议室成员关联
 * 
 * @author xujq
 * @date 2024-04-27 18:54:16
 */
@Mapper
public interface RoomMemberRelationMapper extends BaseMapper<RoomMemberRelation> {
	
}
