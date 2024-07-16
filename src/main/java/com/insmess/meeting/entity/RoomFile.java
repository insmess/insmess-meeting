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
 * 会议资料
 * 
 * @author xujq
 * @email ${email}
 * @date 2024-05-12 17:16:45
 */
@Data
@TableName("room_file")
@ApiModel(value = "会议资料")
public class RoomFile implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty("id")
    @TableField("`id`")
    private String id;
	/**
	 * 文件名
	 */
	@ApiModelProperty("文件名")
    @TableField("`filename`")
    private String filename;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
    @TableField("`create_time`")
    private Date createTime;
	/**
	 * 创建人
	 */
	@ApiModelProperty("创建人")
    @TableField("`create_user_id`")
    private String createUserId;
	/**
	 * 类型(扩展名)
	 */
	@ApiModelProperty("类型(扩展名)")
    @TableField("`file_type`")
    private String fileType;
	/**
	 * 文件url
	 */
	@ApiModelProperty("文件url")
    @TableField("`file_url`")
    private String fileUrl;
	/**
	 * 会议id
	 */
	@ApiModelProperty("会议id")
    @TableField("`room_id`")
    private String roomId;
	/**
	 * 上级目录id，如无上级，则为NULL
	 */
	@ApiModelProperty("上级目录id，如无上级，则为NULL")
    @TableField("`parent_id`")
    private String parentId;
	/**
	 * 实体类型 0为目录 1为文件
	 */
	@ApiModelProperty("实体类型 0为目录 1为文件")
    @TableField("`entity_type`")
    private Integer entityType;
	/**
	 * 文件大小，单位为字节
	 */
	@ApiModelProperty("文件大小，单位为字节")
    @TableField("`file_size`")
    private Long fileSize;

}
