package com.insmess.meeting.common.exception;

import com.insmess.meeting.common.api.IErrorCode;

/**
 * 自定义API异常
 * @author xujq
 */
public class InsmessException extends RuntimeException {
    private IErrorCode errorCode;

    public InsmessException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public InsmessException(String message) {
        super(message);
    }

    public InsmessException(Throwable cause) {
        super(cause);
    }

    public InsmessException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
