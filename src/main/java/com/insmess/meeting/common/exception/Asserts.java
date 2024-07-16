package com.insmess.meeting.common.exception;


import com.insmess.meeting.common.api.IErrorCode;

/**
 * 断言处理类，用于抛出各种API异常
 * @author xujq
 */
public class Asserts {
    public static void fail(String message) {
        throw new InsmessException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new InsmessException(errorCode);
    }
}
