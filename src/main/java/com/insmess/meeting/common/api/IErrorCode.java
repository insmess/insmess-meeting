package com.insmess.meeting.common.api;

/**
 * 常用API返回对象接口
 * @author xujq
 */
public interface IErrorCode {
    /**
     * 获取状态码
     * @return 返回码
     */
    Integer getCode();

    /**
     * 获取信息
     * @return 返回信息
     */
    String getMessage();
}
