package com.insmess.meeting.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



import java.io.Serializable;
import lombok.Data;

/**
 * 会议室成员关联
 * 
 * @author xujq
 * @email ${email}
 * @date 2024-04-27 18:54:16
 */
@Data
@TableName("room_member_relation")
@ApiModel(value = "会议室成员关联")
public class RoomMemberRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty("id")
    @TableField("`id`")
    private String id;
	/**
	 * 会议室id
	 */
	@ApiModelProperty("会议室id")
    @TableField("`room_id`")
    private String roomId;
	/**
	 * 用户名
	 */
	@ApiModelProperty("用户名")
    @TableField("`username`")
    private String username;

}
