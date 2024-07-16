package com.insmess.meeting.common.entity;

import lombok.Data;

import java.util.Date;

/**
 * 对象存储实体
 * @author xjq
 */
@Data
public class StorageEntity {
    //文件名
    private String fileName;
    //url
    private String url;
    //上传后唯一的文件名
    private String key;
    //文件大小
    private Long size;
    //文件类型
    private String type;
    //创建时间
    private Date createTime;
}
