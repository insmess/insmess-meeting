package com.insmess.meeting.common.api;

/**
 * 常用API返回对象
 * @author xujq
 */
public enum ResultCode implements IErrorCode {
    /**
     * 状态码为200 返回信息为操作成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 状态码为500 返回信息为操作失败
     */
    FAILED(500, "操作失败"),
    /**
     * 状态码为400 返回信息为参数检验失败
     */
    VALIDATE_FAILED(400, "参数检验失败"),
    /**
     * 状态码为401 返回信息为暂未登录或token已经过期
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /**
     * 状态码为403 返回信息为没有相关权限
     */
    FORBIDDEN(403, "没有相关权限");
    private Integer code;
    private String message;

    private ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
