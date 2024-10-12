package com.insmess.meeting.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;

import java.io.Serializable;
import lombok.Data;

/**
 * 会议室
 * 
 * @author xujq
 * @email ${email}
 * @date 2024-04-27 18:54:16
 */
@Data
@TableName("room")
@ApiModel(value = "会议室")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty("id")
    @TableField("`id`")
    private String id;
	/**
	 * 会议名称
	 */
	@ApiModelProperty("会议名称")
    @TableField("`name`")
    private String name;
	/**
	 * 会议描述
	 */
	@ApiModelProperty("会议描述")
	@TableField("`description`")
	private String description;
	/**
	 * 会议时长
	 */
	@ApiModelProperty("会议时长")
	@TableField("`duration`")
	private Integer duration;
	/**
	 * 主持人id
	 */
	@ApiModelProperty("主持人id")
    @TableField("`host_username`")
    private String hostUsername;
	/**
	 * 主持人名称
	 */
	@ApiModelProperty("主持人名称")
    @TableField("`host_realname`")
    private String hostRealname;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
    @TableField("`create_time`")
    private Date createTime;
	/**
	 * 开始时间
	 */
	@ApiModelProperty("开始时间")
    @TableField("`start_time`")
    private Date startTime;
	/**
	 * 创建人id
	 */
	@ApiModelProperty("创建人id")
    @TableField("`create_username`")
    private String createUsername;
	/**
	 * 创建人名称
	 */
	@ApiModelProperty("创建人名称")
    @TableField("`create_realname`")
    private String createRealname;
	/**
	 * 房间密码
	 */
	@ApiModelProperty("房间密码")
    @TableField("`room_password`")
    private String roomPassword;
	/**
	 * 0公开会议 1私密会议
	 */
	@ApiModelProperty("0公开会议 1私密会议")
    @TableField("`room_type`")
    private Integer roomType;

}
